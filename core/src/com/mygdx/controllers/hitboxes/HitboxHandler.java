package com.mygdx.controllers.hitboxes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mygdx.Utils;

public class HitboxHandler {
    private final CopyOnWriteArrayList<Collider> colliders = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<Tags, CopyOnWriteArrayList<Hitbox>> hitboxes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Couple, Boolean> contacts = new ConcurrentHashMap<>();

    // USA SETS
    public HitboxHandler() {
        Utils.setHitboxHandler(this);
    }

    public void registerHitbox(Hitbox h) {
        for (Tags tag : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.computeIfAbsent(tag, k -> new CopyOnWriteArrayList<>());
            list.add(h);
        }
    }

    public void registerCollider(Collider r) {
        colliders.add(r);
    }

    public void unRegisterHitbox(Hitbox h) {
        for (Tags tag : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.get(tag);
            if (list != null) {
                list.remove(h);
            }
        }

        for (Couple couple : contacts.keySet()) {
            if (!couple.contains(h))
                continue;
            // couple.getCollider().onLeave(h);
            contacts.remove(couple);
        }
    }

    public void unRegisterCollider(Collider r) {
        colliders.remove(r);
        for (Couple couple : contacts.keySet()) {
            if (!couple.contains(r))
                continue;
            // couple.getHitbox().onLeave(r);
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
                hit = h.isHit(c, activate) || hit;
            }
        }
        return hit;
    }

    public boolean checkWith(Collider c, boolean activate, Tags... searchTags) {
        boolean hit = false;
        for (Tags tag : searchTags) {
            CopyOnWriteArrayList<Hitbox> list = hitboxes.get(tag);
            if (list == null)
                continue;
            for (Hitbox h : list) {
                hit = h.isHit(c, activate) || hit;
            }
        }
        return hit;
    }

    public boolean checkDefault(Collider c, boolean activate) {
        if (c.containsSearchTag(Tags.ALL)) {
            return checkAll(c, activate);
        }
        Tags[] tagsArr = new Tags[c.getSearchTags().size()];
        c.getSearchTags().toArray(tagsArr);
        return checkWith(c, activate, tagsArr);
    }

    public boolean checkWithOffset(Collider c, boolean activate, float x, float y, Tags... searchTags) {
        boolean hit;
        c.translate(x, y);
        if (searchTags[0].equals(Tags.ALL)) {
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