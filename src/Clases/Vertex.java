package Clases;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Ideapad
 */
import java.util.*;

public class Vertex {
    private User user;
    private List<Edge> edges;

    public Vertex(User user) {
        this.user = user;
        this.edges = new LinkedList<>();
    }

    public User getUser() {
        return user;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void addEdge(Vertex destination) {
        Edge edge = new Edge(this, destination);
        if (!edges.contains(edge)) {
            edges.add(edge);
        }
    }

    public void removeEdgeTo(Vertex destination) {
        edges.removeIf(e -> e.getDestination().equals(destination));
    }

    public boolean isAdjacentTo(Vertex destination) {
        return edges.stream().anyMatch(e -> e.getDestination().equals(destination));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex)) return false;
        Vertex other = (Vertex) obj;
        return this.user.equals(other.user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }

    @Override
    public String toString() {
        return user.toString();
    }
}
