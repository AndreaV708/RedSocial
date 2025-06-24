
import Clases.Graph;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo Ideapad
 */
public class Main {
    public static void main(String[] args) {
        Graph social = new Graph();

        social.addUser("u1", "Ana");
        social.addUser("u2", "Luis");
        social.addUser("u3", "Marta");
        social.addUser("u4", "Carlos");
        social.addUser("u5", "Elena");

        social.addFriendship("u1", "u2");
        social.addFriendship("u1", "u3");
        social.addFriendship("u2", "u3");
        social.addFriendship("u3", "u4");

        System.out.println("Mutual friends Ana & Marta: " + social.mutualFriends("u1", "u3"));
        System.out.println("Suggestions for Carlos: " + social.suggestFriends("u4"));
        System.out.println("Shortest path Ana to Carlos: " + social.shortestPath("u1", "u4"));
        System.out.println("Communities: " + social.detectCommunities());
    }
}

