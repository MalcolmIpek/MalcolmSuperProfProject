package cga.exercise.components.geometry

import org.lwjgl.opengl.ARBVertexArrayObject.glBindVertexArray
import org.lwjgl.opengl.ARBVertexArrayObject.glGenVertexArrays
import org.lwjgl.opengl.GL15.*
import org.lwjgl.opengl.GL20.glEnableVertexAttribArray
import org.lwjgl.opengl.GL20.glVertexAttribPointer
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
    private var vao = 0
    private var vbo = 0
    private var ibo = 0
    private var index = 0

    init {
        index = indexdata.size
        vao = glGenVertexArrays()
        glBindVertexArray(vao)

        vbo = glGenBuffers()
        glBindBuffer(GL_ARRAY_BUFFER, vbo)
        glBufferData(GL_ARRAY_BUFFER, vertexdata, GL_STATIC_DRAW) //static zeichne einmal und nie wieder

        for (i in attributes.indices) {
            glVertexAttribPointer(i, attributes[i].n, attributes[i].type, true, attributes[i].stride, attributes[i].offset.toLong())
            glEnableVertexAttribArray(i)
        }
        glBindVertexArray(vao)


        ibo = glGenBuffers()
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo)
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexdata, GL_STATIC_DRAW)
        //bind the ibo and vbo to the vao
        glBindVertexArray(vao)

        //unbind the vao
        glBindVertexArray(0)
        // unbind the vbo
        glBindBuffer(GL_ARRAY_BUFFER, 0)
        // unbind the ibo
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0)
    }

    fun render() {
        glBindVertexArray(vao)
        glDrawElements(GL_TRIANGLES, index, GL_UNSIGNED_INT, index.toLong()) //muss ins long gecastet werden da wir einen long fordern und n int geben
        glBindVertexArray(0) // zeigen den buffer auf die 0 bedeutet, dass wir ihn in den 0 array binden der nicht existiert heißt wir ziehen den stecker / unbinden
    }

    /**
     * Deletes the previously allocated OpenGL objects for this mesh
     */
    fun cleanup() {
        if (ibo != 0) glDeleteBuffers(ibo)
        if (vbo != 0) glDeleteBuffers(vbo)
        if (vao != 0) GL30.glDeleteVertexArrays(vao)
    }
}