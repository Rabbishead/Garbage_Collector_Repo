package com.mygdx.hitboxes;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mygdx.Utils;

public class HitboxHandler {
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Collider>> collidersA = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Hitbox>> hitboxesA = new ConcurrentHashMap<>();
    // private final CopyOnWriteArrayList<Hitbox> hitboxes = new
    // CopyOnWriteArrayList<>();
    // private final CopyOnWriteArrayList<Collider> colliders = new
    // CopyOnWriteArrayList<>();

    public HitboxHandler() {
        Utils.setHitboxHandler(this);
    }

    public void registerHitbox(Hitbox h) {
        for (String string : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxesA.get(string);
            if (list == null) {
                list = new CopyOnWriteArrayList<>();
                hitboxesA.put(string, list);
            }
            list.add(h);
        }
        // hitboxes.add(h);
    }

    public void registerCollider(Collider r) {
        for (String string : r.getTags()) {
            CopyOnWriteArrayList<Collider> list = collidersA.get(string);
            if (list == null) {
                list = new CopyOnWriteArrayList<>();
                collidersA.put(string, list);
            }
            list.add(r);
        }
        // colliders.add(r);
    }

    public void unRegisterHitbox(Hitbox h) {
        for (String string : h.getTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxesA.get(string);
            if (list != null) {
                list.remove(h);
            }
        }
        // hitboxes.remove(h);
    }

    public void unRegisterCollider(Collider r) {
        for (String string : r.getTags()) {
            CopyOnWriteArrayList<Collider> list = collidersA.get(string);
            if (list != null) {
                list.remove(r);
            }
        }
        // colliders.remove(r);
    }

    public void checkHitboxes() {
        for (CopyOnWriteArrayList<Collider> listC : collidersA.values()) {
            for (Collider c : listC) {
                checkForDefault(c);
            }
        }
    }

    public void checkForAll(Collider c) {
        for (CopyOnWriteArrayList<Hitbox> list : hitboxesA.values()) {
            for (Hitbox h : list) {
                h.onHit(c);
            }
        }
    }

    public void checkForDefault(Collider c) {
        if (c.getSearchTags()[0].equals("all")) {
            checkForAll(c);
            return;
        }
        for (String tag : c.getSearchTags()) {
            CopyOnWriteArrayList<Hitbox> list = hitboxesA.get(tag);
            if (list != null)
                for (Hitbox h : list) {
                    h.onHit(c);
                }
        }
    }

    public void checkForTags(Collider c, String searchTags) {
        if (searchTags.contains("all")) {
            checkForAll(c);
            return;
        }
        for (String tag : searchTags.split(",")) {
            CopyOnWriteArrayList<Hitbox> list = hitboxesA.get(tag);
            if (list != null)
                for (Hitbox h : list) {
                    h.onHit(c);
                }
        }
    }

    public void checkHitbox(Collider c, Hitbox h) {
        h.onHit(c);
    }
}