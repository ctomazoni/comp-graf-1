package computacao_grafica.exerc02;

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
    private int zoom = 400;
    private int deslocamentoHorizontal = 0;
    private int deslocamentoVertical = 0;

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
        glu.gluOrtho2D(-zoom, zoom, -zoom, zoom);

        SRU();

        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glPointSize(2.0f);
        gl.glBegin(GL.GL_POINTS);
            for (int i = 0; i < 72; i++) {
                gl.glVertex2d(retornaX((360/72)*i, 100)+deslocamentoHorizontal, retornaY((360/72)*i, 100)+deslocamentoVertical);
            }
        gl.glEnd();
        gl.glFlush();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("--- keyPressed ---");
        switch (e.getKeyCode()) {
            case 'I' :
                aproxima();
                break;
            case 'O' :
                afasta();
                break;
            case 'E' :
                deslocarParaEsquerda();
                break;
            case 'D' :
                deslocarParaDireita();
                break;
            case 'C' :
                deslocarParaCima();
                break;
            case 'B' :
                deslocarParaBaixo();
                break;
            case 'P' :
                padrao();
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
            gl.glVertex2f(-200.0f+deslocamentoHorizontal, 0.0f+deslocamentoVertical);
            gl.glVertex2f( 200.0f+deslocamentoHorizontal, 0.0f+deslocamentoVertical);
        gl.glEnd();

        // eixo y
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
            gl.glVertex2f(0.0f+deslocamentoHorizontal, -200.0f+deslocamentoVertical);
            gl.glVertex2f(0.0f+deslocamentoHorizontal,  200.0f+deslocamentoVertical);
        gl.glEnd();
    }

    public double retornaX(double angulo, double raio) {
        return (raio * Math.cos(Math.PI * angulo / 180.0));
    }

    public double retornaY(double angulo, double raio) {
        return (raio * Math.sin(Math.PI * angulo / 180.0));
    }

    public void aproxima() {
        if (zoom > 70) {
            zoom -= 10;
        }
        glDrawable.display();
    }

    public void afasta() {
        if (zoom < 800) {
            zoom += 10;
        }
        glDrawable.display();
    }

    public void deslocarParaDireita() {
        if (deslocamentoHorizontal < 500) {
            deslocamentoHorizontal += 10;
        }
        glDrawable.display();
    }

    public void deslocarParaEsquerda() {
        if (deslocamentoHorizontal > -500) {
            deslocamentoHorizontal -= 10;
        }
        glDrawable.display();
    }

    public void deslocarParaCima() {
        if (deslocamentoVertical < 500) {
            deslocamentoVertical += 10;
        }
        glDrawable.display();
    }

    public void deslocarParaBaixo() {
        if (deslocamentoVertical > -500) {
            deslocamentoVertical -= 10;
        }
        glDrawable.display();
    }

    public void padrao() {
        zoom = 400;
        deslocamentoHorizontal = 0;
        deslocamentoVertical = 0;
        glDrawable.display();
    }

}
