import processing.core.PConstants._
import processing.core._

class Main extends PApplet {
  private val saveFrames: Boolean = false;

  override def setup() = {

    background(255)
    stroke(200, 0, 100, 2)
    rectMode(CORNERS)
  }

  override def draw() = {
    renderer(frameCount / 100.0f)
    if(saveFrames)
      saveFrame("stills/output-####.tga")
  }

  override def settings = {
    size(600, 800);
  }

  def renderer(initial: Float) = {
    noFill
    (1 to 50).foreach { i =>
      stroke(i, i * 2, 50 + i, 2)
      ellipse(width / 2.0f, height / 2.0f, i * initial, i * 20 * noise(10 - initial))
    }
  }
}

object Main extends PApplet {
  def main(args: Array[String]) = {
    val test = new Main()
    PApplet.runSketch(Array("Main"), test)
  }
}
