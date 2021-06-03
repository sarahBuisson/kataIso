package com.isograd.utils

import java.util.HashMap
import java.util.function.BiFunction
import java.util.stream.Collectors
import java.util.Arrays
import java.util.ArrayList

class Dikjstra {
    private lateinit var graph: Array<IntArray>
    private lateinit var distanceFromStart: IntArray
    private lateinit var activesNodes: BooleanArray
    private var dim = 0
    private lateinit var precedences: IntArray
    private fun activeAdjacents(node: Int) {
        var distanceTo: Int = 0
        for (to in 0 until dim) {
            if (isAdjacent(node, to) && distanceFromStart[node] + graph[node][to].also { distanceTo = it } < distanceFromStart[to]) {
                activeNode(node, to, distanceTo)
            }
        }
    }

    private fun activeNode(from: Int, node: Int, distance: Int) {
        distanceFromStart[node] = distance
        precedences[node] = from
        activesNodes[node] = true
    }

    private fun buildPath(end: Int): List<Int> {
        val path: MutableList<Int> = ArrayList()
        path.add(end)

        // utilisation d'une boucle do-while pour conserver le point de depart
        // et d'arrivee dans la liste même lorsque le point de depart correspond
        // au point d'arrivee
        var position = end
        do {
            path.add(0, precedences[position])
            position = path[0]
        } while (distanceFromStart[position] != 0)
        return path
    }

    /**
     * {@inheritDoc}
     */
    fun getPath(graph: Array<IntArray>, start: Int, end: Int): List<Int>? {
        return this.getPath(graph, intArrayOf(start), intArrayOf(end))
    }

    /**
     * {@inheritDoc}
     */
    fun getPath(graph: Array<IntArray>, start: Int, ends: IntArray?): List<Int>? {
        return this.getPath(graph, intArrayOf(start), ends)
    }

    /**
     * {@inheritDoc}
     */
    fun getPath(graph: Array<IntArray>, starts: IntArray, ends: IntArray?): List<Int>? {
        Arrays.sort(ends)

        // initialisation des variables necessaires a la resolution du probleme
        init(graph, starts)

        // calcul des distances par rapport au point de depart et recuperation
        // du point d'arrivee
        val end = processDistances(ends)
        return if (end != -1) buildPath(end) else null
    }

    private fun init(graph: Array<IntArray>, start: IntArray) {
        this.graph = graph
        dim = graph.size
        activesNodes = BooleanArray(dim)
        precedences = IntArray(dim)
        Arrays.fill(precedences, -1)
        distanceFromStart = IntArray(dim)
        Arrays.fill(distanceFromStart, Int.MAX_VALUE)
        for (value in start) activeNode(value, value, 0)
    }

    private fun isAdjacent(from: Int, to: Int): Boolean {
        return graph[from][to] >= 0
    }

    private fun processDistances(ends: IntArray?): Int {
        // selectionne le prochain noeud a analyser (noeud courant)
        val next = selectNextNode()

        // return -1 s'il n'y a plus de noeud a analyser, donc qu'il n'existe
        // pas de chemin satisfaisant la recherche
        if (next == -1) return -1

        // retourne la position du noeud actuel s'il appartient au tableau des
        // destinations possibles
        if (Arrays.binarySearch(ends, next) >= 0) return next

        // active les prochains noeuds a analyser a partir du noeud courant
        activeAdjacents(next)

        // desactive le noeud courant
        activesNodes[next] = false

        // appel recursif de la methode pour traiter le prochain noeud
        return processDistances(ends)
    }

    private fun selectNextNode(): Int {
        var nextNode = -1
        for (node in 0 until dim) if (activesNodes[node] && (nextNode == -1 || distanceFromStart[node] < distanceFromStart[nextNode])) nextNode = node
        return nextNode
    }
}