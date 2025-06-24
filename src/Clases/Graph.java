package Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Ideapad
 */
import Clases.User;
import Clases.Vertex;
import java.util.*;

public class Graph {
    private Map<String, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public boolean addUser(String userId, String name) {
        if (vertices.containsKey(userId)) return false;
        vertices.put(userId, new Vertex(new User(userId, name)));
        return true;
    }

    public boolean removeUser(String userId) {
        Vertex toRemove = vertices.get(userId);
        if (toRemove == null) return false;

        for (Vertex v : vertices.values()) {
            v.removeEdgeTo(toRemove);
        }

        return vertices.remove(userId) != null;
    }

    public boolean addFriendship(String id1, String id2) {
        Vertex v1 = vertices.get(id1);
        Vertex v2 = vertices.get(id2);
        if (v1 == null || v2 == null || v1.equals(v2)) return false;

        v1.addEdge(v2);
        v2.addEdge(v1);
        return true;
    }

    public boolean removeFriendship(String id1, String id2) {
        Vertex v1 = vertices.get(id1);
        Vertex v2 = vertices.get(id2);
        if (v1 == null || v2 == null) return false;

        v1.removeEdgeTo(v2);
        v2.removeEdgeTo(v1);
        return true;
    }

    public boolean areFriends(String id1, String id2) {
        Vertex v1 = vertices.get(id1);
        Vertex v2 = vertices.get(id2);
        return v1 != null && v2 != null && v1.isAdjacentTo(v2);
    }

    public Set<String> mutualFriends(String id1, String id2) {
        Set<String> mutual = new HashSet<>();
        Vertex v1 = vertices.get(id1);
        Vertex v2 = vertices.get(id2);

        if (v1 == null || v2 == null) return mutual;

        Set<Vertex> friends1 = new HashSet<>();
        for (Edge e : v1.getEdges()) {
            friends1.add(e.getDestination());
        }

        for (Edge e : v2.getEdges()) {
            Vertex f = e.getDestination();
            if (friends1.contains(f)) {
                mutual.add(f.getUser().getUserId());
            }
        }

        return mutual;
    }

    public Set<String> suggestFriends(String id) {
        Set<String> suggestions = new HashSet<>();
        Vertex user = vertices.get(id);
        if (user == null) return suggestions;

        Set<Vertex> directFriends = new HashSet<>();
        for (Edge e : user.getEdges()) {
            directFriends.add(e.getDestination());
        }

        for (Vertex friend : directFriends) {
            for (Edge foafEdge : friend.getEdges()) {
                Vertex candidate = foafEdge.getDestination();
                if (!candidate.equals(user) && !directFriends.contains(candidate)) {
                    suggestions.add(candidate.getUser().getUserId());
                }
            }
        }

        return suggestions;
    }

    public List<String> shortestPath(String fromId, String toId) {
        Vertex start = vertices.get(fromId);
        Vertex end = vertices.get(toId);
        if (start == null || end == null) return null;

        Queue<Vertex> queue = new LinkedList<>();
        Map<Vertex, Vertex> prev = new HashMap<>();
        Set<Vertex> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            if (current.equals(end)) break;

            for (Edge edge : current.getEdges()) {
                Vertex neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    prev.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        if (!prev.containsKey(end)) return null;

        LinkedList<String> path = new LinkedList<>();
        for (Vertex at = end; at != null; at = prev.get(at)) {
            path.addFirst(at.getUser().getUserId());
        }

        return path;
    }

    public List<Set<String>> detectCommunities() {
        Set<Vertex> visited = new HashSet<>();
        List<Set<String>> communities = new ArrayList<>();

        for (Vertex v : vertices.values()) {
            if (!visited.contains(v)) {
                Set<String> community = new HashSet<>();
                Queue<Vertex> queue = new LinkedList<>();
                queue.add(v);
                visited.add(v);

                while (!queue.isEmpty()) {
                    Vertex current = queue.poll();
                    community.add(current.getUser().getUserId());

                    for (Edge e : current.getEdges()) {
                        Vertex neighbor = e.getDestination();
                        if (!visited.contains(neighbor)) {
                            visited.add(neighbor);
                            queue.add(neighbor);
                        }
                    }
                }

                communities.add(community);
            }
        }

        return communities;
    }
}
