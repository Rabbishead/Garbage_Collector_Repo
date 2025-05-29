package com.mygdx.controllers.hitboxes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mygdx.Utils;

public class HitboxHandler {
    private final CopyOnWriteArrayList<Collider> colliders = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<String, CopyOnWriteArrayList<Hitbox>> hitboxes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Couple, Boolean> contacts = new ConcurrentHashMap<>();

    public HitboxHandler() {
        Utils.setHitboxHandler(this);
    }

    public void registerHitbox(Hitbox h) {
        for (String string : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.computeIfAbsent(string, k -> new CopyOnWriteArrayList<>());
            list.add(h);
        }
    }

    public void registerCollider(Collider r) {
        colliders.add(r);
    }

    public void unRegisterHitbox(Hitbox h) {
        for (String string : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.get(string);
            if (list != null) {
                list.remove(h);
            }
        }

        for (Couple couple : contacts.keySet()) {
            if (!couple.contains(h))
                continue;
            //couple.getCollider().onLeave(h);
            contacts.remove(couple);
        }
    }

    public void unRegisterCollider(Collider r) {
        colliders.remove(r);
        for (Couple couple : contacts.keySet()) {
            if (!couple.contains(r))
                continue;
            //couple.getHitbox().onLeave(r);
            contacts.remove(couple);
        }
    }

    public void storeContact(Collider r, Hitbox h) {
        contacts.put(new Couple(h, r), true);
    }

    public void removeContact(Collider r, Hitbox h) {
        Couple key = new Couple(h, r);
        if (!contacts.containsKey(key))
            return;
        contacts.remove(key);
        r.onLeave(h);
        h.onLeave(r);
    }

    public boolean checkHitbox(Collider c, Hitbox h, boolean activate) {
        return h.isHit(c, activate);
    }

    public void checkRegistered() {
        for (Collider c : colliders) {
            checkDefault(c, true);
        }
    }

    public boolean checkAll(Collider c, boolean activate) {
        boolean hit = false;
        for (CopyOnWriteArrayList<Hitbox> list : hitboxes.values()) {
            for (Hitbox h : list) {
                hit = hit || h.isHit(c, activate);
            }
        }
        return hit;
    }

    public boolean checkWith(Collider c, boolean activate, String searchTags) {
        return checkWith(c, activate, searchTags.split(","));
    }

    public boolean checkWith(Collider c, boolean activate, String[] searchTags) {
        boolean hit = false;
        for (String tag : searchTags) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.get(tag);
            if (list == null)
                continue;
            for (Hitbox h : list) {
                hit = hit || h.isHit(c, activate);
            }
        }
        return hit;
    }

    public boolean checkDefault(Collider c, boolean activate) {
        if (c.getSearchTags()[0].equals("all")) {
            return checkAll(c, activate);
        }
        return checkWith(c, activate, c.getSearchTags());
    }

    public boolean checkWithOffset(Collider c, boolean activate, float x, float y, String searchTags) {
        boolean hit;
        c.translate(x, y);
        if (searchTags.contains("all")) {
            hit = checkAll(c, activate);
        } else
            hit = checkWith(c, activate, searchTags);
        c.translate(-x, -y);
        return hit;
    }

    public void clearContacts() {
        contacts.clear();
        for (Collider r : colliders) {
            r.clearKeys();
        }
    }
}