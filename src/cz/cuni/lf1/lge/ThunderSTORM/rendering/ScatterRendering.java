package cz.cuni.lf1.lge.ThunderSTORM.rendering;

import cz.cuni.lf1.lge.ThunderSTORM.estimators.PSF.Molecule;
import ij.process.FloatProcessor;
import java.util.Vector;

/**
 * Simple rendering using scatter plot. If there is any molecule at the pixel
 * location, the pixel value will be a constant positive number and zero
 * otherwise.
 */
public class ScatterRendering extends AbstractRendering implements IncrementalRenderingMethod {

    private ScatterRendering(Builder builder) {
        super(builder);
    }

    public static class Builder extends AbstractBuilder<Builder, ScatterRendering> {

        @Override
        public ScatterRendering build() {
            super.validate();
            return new ScatterRendering(this);
        }
    }

    @Override
    protected void drawPoint(double x, double y, double z, double dx) {
        if(isInBounds(x, y)) {
            int u = (int) ((x - xmin) / resolution);
            int v = (int) ((y - ymin) / resolution);
            int w = (int) ((z - zFrom) / zStep);
            if(w >= 0 && w < zSlices) {
                FloatProcessor img = (FloatProcessor) slices[w];
                img.setf(u, v, Float.MAX_VALUE);
            }
        }
    }
}
