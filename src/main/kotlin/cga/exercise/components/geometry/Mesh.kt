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
        vao = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(vao)
        //Erstellen Sie ein neues VBO und binden Sie es.
        vbo = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo)
        //Speichern Sie die Daten in das VBO.
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertexdata, GL15.GL_STATIC_DRAW)
        //Erstellen Sie ein neues IBO und binden Sie es.
        ibo = GL15.glGenBuffers()
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo)
        //Speichern Sie die Daten in das IBO.
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indexdata, GL15.GL_STATIC_DRAW)
        //Binden Sie die Attribute.
        for ((index, attribute) in attributes.withIndex()) {
            GL20.glVertexAttribPointer(index, attribute.n, GL11.GL_FLOAT, false, attribute.stride, attribute.offset.toLong())
            GL20.glEnableVertexAttribArray(index)
        }
        //Binden Sie das IBO zurück.
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0)
        //Binden Sie das VBO zurück.
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)
        //Binden Sie den VAO zurück.
        GL30.glBindVertexArray(0)
        //Speichern Sie die Anzahl der Indices.
        indexcount = indexdata.size


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