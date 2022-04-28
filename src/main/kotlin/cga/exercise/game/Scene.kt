package cga.exercise.game

import cga.exercise.components.geometry.Mesh
import cga.exercise.components.shader.ShaderProgram
import cga.framework.GameWindow
import cga.exercise.components.geometry.VertexAttribute
import cga.framework.GLError
import org.lwjgl.opengl.GL11.*
import cga.framework.OBJLoader
import org.lwjgl.glfw.GLFW




/**
 * Created by Fabian on 16.09.2017.
 */
class Scene(private val window: GameWindow) {   //var mesh: Mesh
    private val staticShader: ShaderProgram = ShaderProgram("assets/shaders/simple_vert.glsl", "assets/shaders/simple_frag.glsl")

    private var meshes = arrayListOf<Mesh>()
    private var mode= 0


    //scene setup
    init {

        //initial opengl state
        glClearColor(0.6f, 1.0f, 1.0f, 1.0f); GLError.checkThrow()  //Hintergrundfarbe Haus
        // glClearColor(0.0f, 0.0f, 0.0f, 1.0f); GLError.checkThrow()  // Hintergrundfarbe Kreis
        glEnable(GL_CULL_FACE); GLError.checkThrow() //Cull-Facing wurde aktiviert
        glFrontFace(GL_CCW); GLError.checkThrow() // Alle Dreiecke, die zur Kamera gerichtet sind, sind entgegen des Uhrzeigersinns definiert.
        glCullFace(GL_BACK); GLError.checkThrow() // Es werden alle Dreiecke verworfen, die nach hinten zeigen
        glEnable(GL_DEPTH_TEST); GLError.checkThrow()
        glDepthFunc(GL_LESS); GLError.checkThrow()

        // Aufgabe 1.2

        val verticesHouse: FloatArray = floatArrayOf(
        // Position             //Color
            -0.5f, -0.5f, 0.0f,     0.0f, 0.0f, 1.0f,
            0.5f, -0.5f, 0.0f,     0.0f, 0.0f, 1.0f,
            0.5f,  0.5f, 0.0f,     0.0f, 1.0f, 0.0f,
            0.0f,  1.0f, 0.0f,     1.0f, 0.0f, 0.0f,
            -0.5f,  0.5f, 0.0f,     0.0f, 1.0f, 0.0f
        )



        val indicesHouse = intArrayOf(
        0, 1, 2,    //erstes Dreieck
        0, 2, 4,    //zweites Dreieck
        4, 2, 3    // drittes Dreieck
        )

        val attrPosHouse = VertexAttribute(3, GL_FLOAT, 24, 0)  // Pointer Position
        val attrColHouse = VertexAttribute(3, GL_FLOAT, 24, 12)  // Pointer Farbe
        val attribute1 = arrayOf(attrPosHouse, attrColHouse)

        meshes.add(Mesh(verticesHouse, indicesHouse, attribute1))

        //Aufgabe 1.2.4
        val verticesName: FloatArray = floatArrayOf(
        // Position von A        //Color
        1.0f, -0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.75f, -0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
            -1.0f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.75f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.61f, 0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.5f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.5f, -0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.25f, -0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.25f, 0.5f, 0.0f, 0.0f, 0.0f, 1.0f,
        -0.25f, 0.75f, 0.0f, 0.0f, 0.0f, 1.0f,
        -1.0f, 0.75f, 0.0f, 0.0f, 0.0f, 1.0f,
        // M
        0.5f, -0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
        0.75f, 0.25f, 0.0f, 0.0f, 0.0f, 1.0f,
        0.75f, 0.75f, 0.0f, 0.0f, 0.0f, 1.0f,
        0.5f, 0.75f, 0.0f, 0.0f, 0.0f, 1.0f
        // I
        )

        val indicesName = intArrayOf(
            0, 1, 2,
            1, 2, 3,
            3, 4, 5,
            5, 6, 8,
            6, 7, 8,
            8, 2, 10,
            8, 10, 9,
            2, 8, 9,
            2, 9, 10,
            // M
            11, 12, 13,
            13, 14, 11
            // I
            )


        val attrPosName = VertexAttribute(3, GL_FLOAT, 24, 0)
        val attrColName = VertexAttribute(3, GL_FLOAT, 24, 12)
        val attributes = arrayOf(attrPosName, attrColName)
        meshes.add(Mesh(verticesName, indicesName, attributes))

        // OBJLoader benutzen um die Sphere reinzuladen // 1.3
        val resSphere = OBJLoader.loadOBJ("assets/models/sphere.obj")
        val objSphere = resSphere.objects[0].meshes[0]

        val attrPos = VertexAttribute(3, GL_FLOAT, 32, 0)
        val attrTC = VertexAttribute(2, GL_FLOAT, 32, 12)
        val attrNorm = VertexAttribute(3, GL_FLOAT, 32, 20)

        val vertexAttributesSphere = arrayOf(attrPos, attrTC, attrNorm)
        meshes.add(Mesh(objSphere.vertexData, objSphere.indexData, vertexAttributesSphere))
    }



    fun render(dt: Float, t: Float) {
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
        staticShader.use()
        if(window.getKeyState(GLFW.GLFW_KEY_0))
            mode = 0 // Haus
        if(window.getKeyState(GLFW.GLFW_KEY_1))
            mode = 1 // Initalien
        if(window.getKeyState(GLFW.GLFW_KEY_2))
            mode = 2 // Kreis
        meshes[mode].render()
    }

    fun update(dt: Float, t: Float) {}

    fun onKey(key: Int, scancode: Int, action: Int, mode: Int) {}

    fun onMouseMove(xpos: Double, ypos: Double) {}


    fun cleanup() {}
}

