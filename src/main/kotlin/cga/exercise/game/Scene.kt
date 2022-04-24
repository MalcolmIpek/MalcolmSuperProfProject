package cga.exercise.game

import cga.exercise.components.geometry.Mesh
import cga.exercise.components.geometry.VertexAttribute
import cga.exercise.components.shader.ShaderProgram
import cga.framework.GLError
import cga.framework.GameWindow
import cga.framework.OBJLoader
import org.lwjgl.opengl.GL11.*


/**
 * Created by Fabian on 16.09.2017.
 */
class Scene(private val window: GameWindow) {
    private val staticShader: ShaderProgram = ShaderProgram("assets/shaders/simple_vert.glsl", "assets/shaders/simple_frag.glsl")

    private var mesh: Mesh

    //scene setup
    init {
        /*
 * Simple class that holds all information about a single vertex attribute
 * @param n         Number of components of this attribute
 * @param type      Type of this attribute
 * @param stride    Size in bytes of a whole vertex
 * @param offset    Offset in bytes from the beginning of the vertex to the location of this attribute data
 */


        val vertices: FloatArray = floatArrayOf(
            // Position               // Color
            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f
        )

        val verticesName: FloatArray = floatArrayOf(
            // Position               // Color
            0.0f, 2.0f, 0.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f,
            2.0f, 2.0f, 0.0f, 0.0f, 0.0f, 1.0f,
            2.0f, 2.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            3.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            4.0f, 2.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            5.0f, 2.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            7.0f, 2.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            6.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            5.0f, 2.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            7.0f, 2.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            6.0f, 3.0f, 0.0f, 1.0f, 0.0f, 0.0f,
        )

        val indices = intArrayOf(
            0, 1, 2,    //erstes Dreieck
            0, 2, 4,    //zweites Dreieck*/
            4, 2, 3    //drittes Dreieck
        )

        val indicesName = intArrayOf(
            0, 1, 2, 2, 3, 4, 5, 6, 7, 5, 6, 8
        )

        val attributes = arrayOf(
            VertexAttribute(3, GL_FLOAT, 6 * 4, 6 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 12 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 18 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 24 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 32 * 4),
        )

        val attributesName = arrayOf(
            VertexAttribute(3, GL_FLOAT, 6 * 4, 6 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 12 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 18 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 24 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 32 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 38 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 44 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 50 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 56 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 62 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 68 * 4),
            VertexAttribute(3, GL_FLOAT, 6 * 4, 74 * 4)
        )

        mesh = Mesh(vertices, indices, attributes)


        //initial opengl state
        glClearColor(0.6f, 1.0f, 1.0f, 1.0f); GLError.checkThrow()
        glDisable(GL_CULL_FACE); GLError.checkThrow()
        //glFrontFace(GL_CCW); GLError.checkThrow()
        //glCullFace(GL_BACK); GLError.checkThrow()
        glEnable(GL_DEPTH_TEST); GLError.checkThrow()
        glDepthFunc(GL_LESS); GLError.checkThrow()

        glEnable(GL_CULL_FACE)
        glFrontFace(GL_CCW)
        glCullFace(GL_BACK)

        var obj = OBJLoader.loadOBJ("assets/models/cube.obj") //a
        val meshlist = obj.objects[0].meshes //b

        for (mesh in meshlist) {
            var vertAttributes = mesh.vertices.map { VertexAttribute(3, GL_FLOAT, 8 * 4, 8 * 4) }
            (mesh as Mesh).render() //e casting und rendern
        }//<- c + d
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f); GLError.checkThrow() //e background color
    }

    fun render(dt: Float, t: Float) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        //implement every object into the render pipeline
        staticShader.use()
        mesh.render()
        staticShader.use()
        //render the mesh


    }

    fun update(dt: Float, t: Float) {}

    fun onKey(key: Int, scancode: Int, action: Int, mode: Int) {}

    fun onMouseMove(xpos: Double, ypos: Double) {}


    fun cleanup() {}
}
