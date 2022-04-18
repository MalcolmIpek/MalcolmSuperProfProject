package cga.exercise.components.geometry

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30

/**
 * Creates a Mesh object from vertexdata, intexdata and a given set of vertex attributes
 *
 * @param vertexdata plain float array of vertex data
 * @param indexdata  index data
 * @param attributes vertex attributes contained in vertex data
 * @throws Exception If the creation of the required OpenGL objects fails, an exception is thrown
 *
 * Created by Fabian on 16.09.2017.
 */
class Mesh(vertexdata: FloatArray, indexdata: IntArray, attributes: Array<VertexAttribute>) {
    //private data
    private var vao = 0
    private var vbo = 0
    private var ibo = 0
    private var indexcount = 0

    init {
        // todo: place your code here
        //1) Erstellen und Binden eines VAO. Jedes VBO und IBO, die Sie in der Sequenz binden, wird dem
        //aktuellen VAO zugeordnet.
        //2) Erstellen und Binden von Vertex- und Indexbuern.
        //3) Füllen Sie die Vertex- und Indexbuer mit den entsprechenden Daten.
        //4) Aktivieren und denieren Sie die jeweiligen VertexAttribute.
        //5) Danach sollte alles gelöst werden (unbind), um versehentliche Änderungen an den Buern und
        //VAO zu vermeiden. Denken Sie daran, das VAO zuerst zu lösen.

        //1)
        vao = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(vao)

        //2)
        vbo = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexdata, GL15.GL_STATIC_DRAW)

        //3)
        for ((index, attribute) in attributes.withIndex()) {
            GL20.glVertexAttribPointer(index, attribute.n, GL11.GL_FLOAT, false, attribute.stride, attribute.offset.toLong())
            GL20.glEnableVertexAttribArray(index)
        }

        //4)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)

        //5)
        GL30.glBindVertexArray(0)

        //6)
        ibo = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexdata, GL15.GL_STATIC_DRAW)

        //7)
        indexcount = indexdata.size


        //1) Binden Sie das VAO.
        //2) Zeichnen Sie die Elemente.
        //3) Lösen der Bindung, um versehentliche Änderungen am VAO zu vermeiden.

        //1)
        GL30.glBindVertexArray(vao)

        //2)
        GL11.glDrawElements(GL11.GL_TRIANGLES, indexcount, GL11.GL_UNSIGNED_INT, 0)

        //3)
        GL30.glBindVertexArray(0)


        // todo: generate IDs
        // todo: bind your objects
        // todo: upload your mesh data
    }

    /**
     * renders the mesh
     */
    fun render() {
        // todo: place your code here
        // call the rendering method every frame
    }

    /**
     * Deletes the previously allocated OpenGL objects for this mesh
     */
    fun cleanup() {
        if (ibo != 0) GL15.glDeleteBuffers(ibo)
        if (vbo != 0) GL15.glDeleteBuffers(vbo)
        if (vao != 0) GL30.glDeleteVertexArrays(vao)
    }
}