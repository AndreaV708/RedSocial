package Clases;


import Clases.Vertex;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Ideapad
 */
public class Edge {
    private Vertex origin;
    private Vertex destination;

    public Edge(Vertex origin, Vertex destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Edge)) return false;
        Edge other = (Edge) obj;
        return this.origin.equals(other.origin) && this.destination.equals(other.destination);
    }

    @Override
    public int hashCode() {
        return origin.hashCode() + destination.hashCode();
    }
}

