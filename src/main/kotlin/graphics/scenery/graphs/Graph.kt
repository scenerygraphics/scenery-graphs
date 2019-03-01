package graphics.scenery.graphs

import graphics.scenery.Mesh
import org.jgrapht.Graph

/**
 * Node to represent graphs in scenery
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
class Graph : Mesh("Graph") {
    init {

    }

    companion object {
        @JvmStatic fun fromJGraphT(graph: Graph<*, *>): graphics.scenery.graphs.Graph {
            return Graph()
        }
    }
}
