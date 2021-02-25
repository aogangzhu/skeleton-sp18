
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private double G = 6.67e-11;


    public Planet(
            double xP, double yP, double xV,
            double yV, double m, String img
    ) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p)
    {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r_2 = dx * dx + dy * dy;
        return Math.sqrt(r_2);
    }

    public double calcForceExertedBy(Planet p)
    {
        double r = this.calcDistance(p);
        double F = (G * this.mass * p.mass) / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet p)
    {
        double F = this.calcForceExertedBy(p);
        double dx =p.xxPos - this.xxPos;
        double r = this.calcDistance(p);
        return (F * dx) / r;
    }

    public double calcForceExertedByY(Planet p)
    {
        double F = this.calcForceExertedBy(p);
        double dy =p.yyPos - this.yyPos;
        double r = this.calcDistance(p);
        return (F * dy) / r;
    }

    public double calcNetForceExertedByX(Planet [] p)
    {
        double F_netX = 0;
        for(int i = 0; i < p.length; i++)
        {
            if(this.equals(p[i])){
                continue;
            }else{
                F_netX += this.calcForceExertedByX(p[i]);
            }
        }
        return F_netX;
    }

    public double calcNetForceExertedByY(Planet [] p)
    {
        double F_netY = 0;
        for(int i = 0; i < p.length; i++)
        {
            if(this.equals(p[i])){
                continue;
            }else{
                F_netY += this.calcForceExertedByY(p[i]);
            }
        }
        return F_netY;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + this.xxVel * dt;
        this.yyPos = this.yyPos + this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
        StdDraw.show();
        //StdDraw.pause(2000);
    }

}