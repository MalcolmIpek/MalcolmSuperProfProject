package cga.exercise.game

import cga.exercise.components.geometry.Mesh
import cga.exercise.components.geometry.VertexAttribute
import cga.exercise.components.shader.ShaderProgram
import cga.framework.GLError
import cga.framework.GameWindow
import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL20.glEnableVertexAttribArray
import org.lwjgl.opengl.GL20.glVertexAttribPointer


/**
 * Created by Fabian on 16.09.2017.
 */
class Scene(private val window: GameWindow) {
    private val staticShader: ShaderProgram

    //scene setup
    init {
        staticShader = ShaderProgram("assets/shaders/simple_vert.glsl", "assets/shaders/simple_frag.glsl")
/*
 * Simple class that holds all information about a single vertex attribute
 * @param n         Number of components of this attribute
 * @param type      Type of this attribute
 * @param stride    Size in bytes of a whole vertex
 * @param offset    Offset in bytes from the beginning of the vertex to the location of this attribute data
 */
        val vertices: FloatArray = floatArrayOf(
            // Position               // Color
            -0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
            0.5f, -0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            -0.5f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f
        )

        val indices = intArrayOf(
            0, 1, 2,    //erstes Dreieck
            0, 2, 4,    //zweites Dreieck
            4, 2, 3     //drittes Dreieck
        )

        val attributes = arrayOf(
            VertexAttribute(3, GL_FLOAT, 3 * 4, 0),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 3 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 6 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 9 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 12 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 15 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 18 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 21 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 24 * 4),
            VertexAttribute(3, GL_FLOAT, 3 * 4, 27 * 4)
        )

        val mesh = Mesh(vertices, indices, attributes)


        //initial opengl state
        glClearColor(0.6f, 1.0f, 1.0f, 1.0f); GLError.checkThrow()
        glDisable(GL_CULL_FACE); GLError.checkThrow()
        //glFrontFace(GL_CCW); GLError.checkThrow()
        //glCullFace(GL_BACK); GLError.checkThrow()
        glEnable(GL_DEPTH_TEST); GLError.checkThrow()
        glDepthFunc(GL_LESS); GLError.checkThrow()
    }

    fun render(dt: Float, t: Float) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }

    fun update(dt: Float, t: Float) {}

    fun onKey(key: Int, scancode: Int, action: Int, mode: Int) {}

    fun onMouseMove(xpos: Double, ypos: Double) {}


    fun cleanup() {}
}
