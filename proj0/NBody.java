public class NBody {

  //filelength defines how many objects in the universe
  public int filelength = 5;

  //return the radius of the universe
  public static double readRadius(String file) {
    In in = new In(file);
    int num = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  //return the univese Body
  public static Body[] readBodies(String file) {
    In in = new In(file);
    Body[] planets = new Body[5];
    in.readInt();
    in.readDouble();
    for (int i = 0; i < 5; i++){
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
    }
    return planets;
  }


  public static void  main(String[] args) {

    //collecting inputs
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Body[] planets = readBodies(filename);

    //draw the background
    String background = "./images/starfield.jpg";
    StdDraw.clear();
    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0, 0, background, 2*radius , 2*radius);
    StdDraw.show();

    //draw planets
    for (int i = 0; i < planets.length ; i++){
      planets[i].draw();
    }

    StdDraw.enableDoubleBuffering();

    // for each enterval st, update the postion of the planets and show them
    for (int t = 0; t <= T; t += dt){
      double[] xForce = new double[5];
      double[] yForce = new double[5];
      for (int j = 0; j < planets.length; j++){
        xForce[j] = planets[j].calcNetForceExertedByX(planets);
        yForce[j] = planets[j].calcNetForceExertedByY(planets);
        planets[j].update(dt, xForce[j], yForce[j]);
      }

      //draw background
      StdDraw.picture(0, 0, background, 2*radius , 2*radius);

      //draw all planets
      for (int i = 0; i < planets.length ; i++){
        planets[i].draw();
      }
      StdDraw.show();
      StdDraw.pause(10);
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }


}
