package util.staticFunctions;

import controller.MainController;
import javafx.scene.shape.Shape;
import model.Connection;
import model.Node;
import view.Main;

/**
 * Function which tries to optimize your mindmap by checking if there
 * is a collision.
 */
public class orderObjects {
    public static void orderNodes() {
        MainController mainController = Main.mainController;
        for (Node nodeOuter : mainController.getMap().getNodes()) {
            double radiusY = nodeOuter.getEllipse().getRadiusY();
            double radiusX = nodeOuter.getEllipse().getRadiusX();
            /**
             * This loop checks if there are Nodes which overlap
             * it tries to fix the problem by shifting the Node left, right, top and bottom
             */
            for (Node nodeInner : mainController.getMap().getNodes()){
                  if (checkNode(nodeOuter.getEllipse(),nodeInner.getEllipse())){
                    for (int factor = 1; factor < 8; factor++){
                        double orgTransX = nodeOuter.getTranslateX();
                        double orgTransY = nodeOuter.getTranslateY();
                        if(shiftRight(nodeOuter, factor, radiusX, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftLeft(nodeOuter, factor, radiusX, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftTop(nodeOuter, factor, radiusY, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftTopLeft(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY)){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftTopRight(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY)){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftBottomRight(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY)){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftBottomLeft(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),nodeInner.getEllipse(),orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                    }
                }
            }
            /**
             * This loop checks if there are Connections which overlap with Nodes
             * it tries to fix the problem by shifting the Node left, right, top and bottom
             */
            for (Connection connectionOuter: mainController.getMap().getConnections()) {
                if (checkNode(nodeOuter.getEllipse(), connectionOuter)) {
                    for (int factor = 1; factor < 8; factor++) {
                        double orgTransX = nodeOuter.getTranslateX();
                        double orgTransY = nodeOuter.getTranslateY();

                        if(shiftRight(nodeOuter, factor, radiusX, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY )){
                            break;
                        } else {
                            resetTranslate(nodeOuter,orgTransX, orgTransY);
                        }
                        if(shiftLeft(nodeOuter, factor, radiusX, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY )){
                            break;
                        } else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftBottom(nodeOuter, factor, radiusY, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftTop(nodeOuter, factor, radiusY, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftTopLeft(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY)){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftTopRight(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY)){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftBottomRight(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY)){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                        if(shiftBottomLeft(nodeOuter, factor,radiusX, radiusY, nodeOuter.getEllipse(),connectionOuter,orgTransX,orgTransY )){
                            break;
                        }else {
                            resetTranslate(nodeOuter,orgTransX,orgTransY);
                        }
                    }
                }
                /**
                 * This loop checks if there are Connections which overlap with other Connections
                 * it tries to fix the problem by shifting the Node left, right, top and bottom
                 */
                for (Connection connectionInner : mainController.getMap().getConnections()) {
                    if (checkNode(connectionOuter, connectionInner)) {
                        for (int factor = 1; factor < 8; factor++) {
                            double orgTransX = nodeOuter.getTranslateX();
                            double orgTransY = nodeOuter.getTranslateY();

                            if (shiftRight(nodeOuter, factor, radiusX, connectionInner, connectionOuter, orgTransX, orgTransY)) {
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if (shiftLeft(nodeOuter, factor, radiusX, connectionInner, connectionOuter, orgTransX, orgTransY)) {
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if (shiftBottom(nodeOuter, factor, radiusY, connectionInner, connectionOuter, orgTransX, orgTransY)) {
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if (shiftTop(nodeOuter, factor, radiusY, connectionInner, connectionOuter, orgTransX, orgTransY)) {
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if(shiftTopLeft(nodeOuter, factor,radiusX, radiusY, connectionInner,connectionOuter,orgTransX,orgTransY)){
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if(shiftTopRight(nodeOuter, factor,radiusX, radiusY, connectionInner,connectionOuter,orgTransX,orgTransY)){
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if(shiftBottomRight(nodeOuter, factor,radiusX, radiusY, connectionInner,connectionOuter,orgTransX,orgTransY)){
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                            if(shiftBottomLeft(nodeOuter, factor,radiusX, radiusY, connectionInner,connectionOuter,orgTransX,orgTransY )){
                                break;
                            }else {
                                resetTranslate(nodeOuter,orgTransX,orgTransY);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * @param node which position gets reseted
     * @param orgTransX original coordinates
     * @param orgTransY original coordinates
     */
    private static void resetTranslate(Node node, double orgTransX, double orgTransY){
        node.setTranslateX(orgTransX);
        node.setTranslateY(orgTransY);
        node.setPosition(orgTransX, orgTransY);
    }

    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusX the distance of a horizontal shift
     * @param radiusY the distance of a vertical shift
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftTopLeft(Node nodeOuter, int factor, double radiusX,  double radiusY, Shape a, Shape b, double orgTransX, double orgTransY){
        return shiftLeft(nodeOuter,factor,radiusX,a,b,orgTransX,orgTransY) &&
                shiftTop(nodeOuter,factor,radiusY,a,b,orgTransX,orgTransY);
    }
    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusX the distance of a horizontal shift
     * @param radiusY the distance of a vertical shift
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftTopRight(Node nodeOuter, int factor, double radiusX,  double radiusY, Shape a, Shape b, double orgTransX, double orgTransY){
       return shiftRight(nodeOuter,factor,radiusX,a,b,orgTransX,orgTransY) &&
                shiftTop(nodeOuter,factor,radiusY,a,b,orgTransX,orgTransY);
    }
    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusX the distance of a horizontal shift
     * @param radiusY the distance of a vertical shift
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftBottomRight(Node nodeOuter, int factor, double radiusX,  double radiusY, Shape a, Shape b, double orgTransX, double orgTransY){
        return shiftRight(nodeOuter,factor,radiusX,a,b,orgTransX,orgTransY) &&
                shiftBottom(nodeOuter,factor,radiusY,a,b,orgTransX,orgTransY);
    }
    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusX the distance of a horizontal shift
     * @param radiusY the distance of a vertical shift
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftBottomLeft(Node nodeOuter, int factor, double radiusX,  double radiusY, Shape a, Shape b, double orgTransX, double orgTransY){
        return shiftLeft(nodeOuter,factor,radiusX,a,b,orgTransX,orgTransY) &&
                shiftBottom(nodeOuter,factor,radiusY,a,b,orgTransX,orgTransY);
    }

    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusX the distance of a shift is the radius of the ellipse
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftRight (Node nodeOuter, int factor, Double radiusX, Shape a, Shape b, double orgTransX, double orgTransY ) {
        nodeOuter.setTranslateX(orgTransX + (radiusX * factor));
        nodeOuter.setPosition(orgTransX + (radiusX * factor), orgTransY);
        if (checkNode(a, b)) {
            return false;
        } else {
            return true;
        }
    }
    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusX the distance of a shift is the radius of the ellipse
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftLeft (Node nodeOuter, int factor, Double radiusX, Shape a, Shape b, double orgTransX, double orgTransY ) {
        nodeOuter.setTranslateX(orgTransX+(radiusX*factor*-1));
        nodeOuter.setPosition(orgTransX+(radiusX*factor*-1),orgTransY);
        if (checkNode(a,b)){
            return false;
        }else {
            return true;
        }
    }

    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusY the distance of a shift is the radius of the ellipse
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftBottom (Node nodeOuter, int factor, Double radiusY, Shape a, Shape b, double orgTransX, double orgTransY ) {
        nodeOuter.setTranslateY(orgTransY+(radiusY*factor*-1));
        nodeOuter.setPosition(orgTransX,orgTransY+(radiusY*factor*-1));
        if (checkNode(a,b)){
            return false;
        }else {
            return true;
        }
    }
    /**
     * @param nodeOuter the OuterNode which will be shifted to correct
     * @param factor how far should the node be shifted
     * @param radiusY the distance of a shift is the radius of the ellipse
     * @param a the shape which gets checked wether it collides or not
     * @param b the other shape which gets checked wether it collides or not
     * @param orgTransX the Original X position of the Object
     * @param orgTransY the Original Y position of the Object
     * @return Returns false if the problem is not solved / true if solved
     */
    private static boolean shiftTop (Node nodeOuter, int factor, Double radiusY, Shape a, Shape b, double orgTransX, double orgTransY ) {
        nodeOuter.setTranslateY(orgTransY+(radiusY*factor));
        nodeOuter.setPosition(orgTransX,orgTransY+(radiusY*factor));
        if (checkNode(a,b)){
            return false;
        }else {
            return true;
        }
    }
    /**
     * @param out defines the outerShape which one will be shifted
     * @param in is the innerShape which is checked if it collides with the outershape
     * @return Return true if there is a collision and false if there is none
     * In this function there is a Shape intersect generated, this shape consists
     * of the overlapping of two objects, if there is no collision there is no collision
     * the Shape will have the size 0.
     */
    private static boolean checkNode(Shape out, Shape in){
        Shape intersect = Shape.intersect(out, in);
        if (intersect.getBoundsInParent().getHeight() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
