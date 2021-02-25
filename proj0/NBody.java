
public class NBody {
    public static void main(String [] args){
        double T = Double.valueOf(args[0]);
        double dt = Double.valueOf(args[1]);
        String filename = args[2];
        double R = readRadius(filename);
        Planet [] p =readPlanets(filename);


        StdDraw.enableDoubleBuffering();

        for( double t = 0; t < T; t += dt){
            double [] xForces = new double[p.length];
            double [] yForces = new double[p.length];
            for(int i=0; i<p.length; i++){
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }
            for(int i=0; i<p.length; i++){
                p[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.setScale(-R, R);
            StdDraw.clear();
            StdDraw.picture(0, 0, "./images/starfield.jpg");
            StdDraw.show();
            //StdDraw.pause(2000);
            for(int i=0; i< p.length; i++){
                p[i].draw();
            }
            //StdDraw.show();
            StdDraw.pause(10);

        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }
    }

    public static double readRadius(String fileName)
    {
        In in = new In(fileName);
        int pNum = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet [] readPlanets(String fileName){
        In in = new In(fileName);
        int pNum = in.readInt();
        double R = in.readDouble();
        Planet [] p = new Planet[pNum];
        for(int i=0; i<pNum; i++)
        {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] =new Planet(xP, yP, xV, yV, m, img);
        }
        return p;
    }


}