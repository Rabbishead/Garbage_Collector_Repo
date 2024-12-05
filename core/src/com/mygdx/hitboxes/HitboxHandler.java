package com.mygdx.hitboxes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mygdx.Utils;

public class HitboxHandler {
    private final CopyOnWriteArrayList<Collider> colliders = new CopyOnWriteArrayList<>();
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Hitbox>> hitboxes = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Boolean> contacts = new ConcurrentHashMap<>();

    public HitboxHandler() {
        Utils.setHitboxHandler(this);
    }

    public void registerHitbox(Hitbox h) {
        for (String string : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.get(string);
            if (list == null) {
                list = new CopyOnWriteArrayList<>();
                hitboxes.put(string, list);
            }
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
    }

    public void unRegisterCollider(Collider r) {
        colliders.remove(r);
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
    }
}