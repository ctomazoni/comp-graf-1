package computacao_grafica.exerc05;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author ctomazoni
 */
public class Main implements GLEventListener, KeyListener {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private int tipoFigura = GL.GL_POINTS;
    private int sequenciaFigura = 0;

    @Override
    public void init(GLAutoDrawable drawable) {
        System.out.println("--- init ---");
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));
        System.out.println("Espaço de desenho com tamanho: " + drawable.getWidth() + " x " + drawable.getHeight());
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public void display(GLAutoDrawable arg0) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-400.0f, 400.0f, -400.0f, 400.0f);

        SRU();

        gl.glPointSize(5.0f);
        gl.glLineWidth(5.0f);
        gl.glBegin(tipoFigura);
            gl.glColor3f(1.0f, 0.0f, 0.0f);
            gl.glVertex2d( 200, -200);
            gl.glColor3f(0.0f, 1.0f, 0.0f);
            gl.glVertex2d( 200,  200);
            gl.glColor3f(0.0f, 0.0f, 1.0f);
            gl.glVertex2d(-200,  200);
            gl.glColor3f(1.0f, 0.0f, 1.0f);
            gl.glVertex2d(-200, -200);
        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("--- keyPressed ---");
        switch (e.getKeyCode()) {
            case ' ' :
                alteraFiguraGeometrica();
                break;
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.out.println("--- reshape ---");
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        System.out.println("--- displayChanged ---");
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        System.out.println("--- keyReleased ---");
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println("--- keyTyped ---");
    }

    public void SRU() {
        // eixo x
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glLineWidth(1.0f);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2f( -200.0f, 0.0f );
            gl.glVertex2f(  200.0f, 0.0f );
        gl.glEnd();

        // eixo y
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(  0.0f, -200.0f);
            gl.glVertex2f(  0.0f, 200.0f );
        gl.glEnd();
    }

    public double retornaX(double angulo, double raio) {
        return (raio * Math.cos(Math.PI * angulo / 180.0));
    }

    public double retornaY(double angulo, double raio) {
        return (raio * Math.sin(Math.PI * angulo / 180.0));
    }

    public void alteraFiguraGeometrica() {
        sequenciaFigura++;
        if (sequenciaFigura >= 10) {
            sequenciaFigura = 0;
        }
        switch (sequenciaFigura) {
            case 0 :
                tipoFigura = GL.GL_POINTS;
                break;
            case 1 :
                tipoFigura = GL.GL_LINES;
                break;
            case 2 :
                tipoFigura = GL.GL_LINE_LOOP;
                break;
            case 3 :
                tipoFigura = GL.GL_LINE_STRIP;
                break;
            case 4 :
                tipoFigura = GL.GL_TRIANGLES;
                break;
            case 5 :
                tipoFigura = GL.GL_TRIANGLE_FAN;
                break;
            case 6 :
                tipoFigura = GL.GL_TRIANGLE_STRIP;
                break;
            case 7 :
                tipoFigura = GL.GL_QUADS;
                break;
            case 8 :
                tipoFigura = GL.GL_QUAD_STRIP;
                break;
            case 9 :
                tipoFigura = GL.GL_POLYGON;
                break;
        }
        glDrawable.display();
    }

}
