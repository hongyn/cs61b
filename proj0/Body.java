public class Body{
/** @param
    xxPos: current x postion
    yyPos: current y positon
    xxVel: current velocity in the x direction
    yyVel: current velocity in the y direction
    mass: mass
    imgFileName: The name of the file that corresponds to the image that depicts the body
*/
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  public double G = 6.67e-11;

  public Body(double xP, double yP, double xV,
              double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
              }

  public Body(Body b){
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;  }

    // calculete the distance
    public double calcDistance(Body b){
      double distance = Math.sqrt(
                        Math.pow((xxPos - b.xxPos), 2) + Math.pow((yyPos - b.yyPos), 2));
      return distance;
    }

    // calcalute the force
    public double calcForceExertedBy(Body b){
      double force = G * mass * b.mass / Math.pow(calcDistance(b), 2);
      return force;
    }

    //calculate the force in X direction
    public double calcForceExertedByX(Body b){
      double xforce = calcForceExertedBy(b) * ((b.xxPos - xxPos) / calcDistance(b));
      return xforce;
    }

    //calculate the force in Y direction
    public double calcForceExertedByY(Body b){
      double yforce = calcForceExertedBy(b) * ((b.yyPos - yyPos) / calcDistance(b));
      return yforce;
    }

    //calculate the net force in X direction
    public double calcNetForceExertedByX(Body[] net){
      double xNetForce = 0;
      for (int i = 0; i < net.length; i++){
        if (!(this.equals(net[i]))){
          xNetForce += calcForceExertedByX(net[i]);
        }
      }
      return xNetForce;
    }

    //calculate the net force in Y direction
    public double calcNetForceExertedByY(Body[] net){
      double yNetForce = 0;
      for (int i = 0; i < net.length; i++){
        if (!(this.equals(net[i]))){
        yNetForce += calcForceExertedByY(net[i]);
        }
      }
      return yNetForce;
    }

    //update the velocity and the postion
    public void update(double dt, double fX, double fY){
      double aX = fX / mass;
      double aY = fY / mass;
      xxVel += dt * aX;
      yyVel += dt * aY;
      xxPos += dt * xxVel;
      yyPos += dt * yyVel;
    }

    //draw bodys
    public void draw(){
      StdDraw.picture(xxPos, yyPos, "./images/"+imgFileName);
    }



}
