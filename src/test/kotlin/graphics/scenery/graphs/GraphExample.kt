package graphics.scenery.graphs

import cleargl.GLVector
import graphics.scenery.*
import graphics.scenery.backends.Renderer
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import org.junit.Test
import java.net.URI
import kotlin.concurrent.thread


/**
 * Example to demonstrate visualising graphs.
 *
 * @author Ulrik Günther <hello@ulrik.is>
 */
class GraphExample : SceneryBase("Graph Example", 1280, 720) {
    override fun init() {
        renderer = Renderer.createRenderer(hub, applicationName, scene, 512, 512)
        hub.add(SceneryElement.Renderer, renderer!!)

        val g = DefaultDirectedGraph<Any, DefaultEdge>(DefaultEdge::class.java)

        val google = URI("http://www.google.com")
        val wikipedia = URI("http://www.wikipedia.org")
        val jgrapht = URI("http://www.jgrapht.org")

        // add the vertices
        g.addVertex(google)
        g.addVertex(wikipedia)
        g.addVertex(jgrapht)

        // add edges to create linking structure
        g.addEdge(jgrapht, wikipedia)
        g.addEdge(google, jgrapht)
        g.addEdge(google, wikipedia)
        g.addEdge(wikipedia, google)

        val graph = Graph.fromJGraphT(g)
        scene.addChild(graph)

        val light = PointLight(radius = 15.0f)
        light.position = GLVector(0.0f, 0.0f, 2.0f)
        light.intensity = 100.0f
        light.emissionColor = GLVector(1.0f, 1.0f, 1.0f)
        scene.addChild(light)

        val cam: Camera = DetachedHeadCamera()
        with(cam) {
            position = GLVector(0.0f, 0.0f, 5.0f)
            perspectiveCamera(50.0f, 512.0f, 512.0f)
            active = true

            scene.addChild(this)
        }

        thread {
            while (true) {
                graph.rotation.rotateByAngleY(0.01f)
                graph.needsUpdate = true

                Thread.sleep(20)
            }
        }
    }

    @Test
    override fun main() {
        super.main()
    }
}
