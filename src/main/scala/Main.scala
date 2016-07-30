import processing.core.PConstants._
import processing.core._

class Main extends PApplet {
  private val saveFrames: Boolean = true

  override def setup() = {

    background(255)
    stroke(200, 0, 100, 2)
    rectMode(CORNERS)
  }

  override def draw() = {
    renderer(frameCount / 100.0f)
    if (saveFrames && frameCount % 1000 == 0)
      saveFrame("stills/output-####.tga")
  }

  override def settings() = {
    size(1600, 900)
  }

  def renderer(initial: Float) = {
    noFill()
    (1 to 50).foreach { i =>
      stroke(i, i * 2, 50 + i, 2)
      ellipse(width / 2.0f, height / 2.0f, i * initial, i * 20 * noise(10 - initial))
    }
  }
}

class Other extends PApplet {
  private var saveFrames: Boolean = false

  override def setup() = {

    background(255)
    stroke(200, 0, 100, 2)
    beginRecord(PDF, "output-####.pdf")

  }
  
  override def mousePressed() = saveFrames = true

  override def draw() = {
  
    renderer(frameCount / 200.0f)
  
    if (saveFrames) {
      endRecord()
      saveFrames = false
    }
  }

  override def settings() = {
    size(1600, 900)
    smooth(8)
  }

  def renderer(initial: Float) = {
    noFill()
    (1 to 50).foreach { i =>
      stroke(i * 5, i * i, i, 1)
      ellipse(width / 2.0f, height / 2.0f, i * 10 * initial * noise(i), i * 20 * noise(i % initial))
    }
  }
}

object Runner extends PApplet {
  def main(args: Array[String]) = {
    val test = new Main()
    val other = new Other()
    PApplet.runSketch(Array("test"), test)

  }
  
}
