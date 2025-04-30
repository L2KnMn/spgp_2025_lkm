package kr.ac.tukorea.ge.scgyong.dragonflight.game;

import kr.ac.tukorea.ge.scgyong.dragonflight.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Score;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.VertScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class AssignmentScene extends Scene {
    private static final String TAG = AssignmentScene.class.getSimpleName();

    public enum Layer {
        bg1, enemy, bullet, fighter, bg2, ui, controller;
        public static final int COUNT = values().length;
    }
    public AssignmentScene() {
        Metrics.setGameSize(1600, 900); // default=900x1600
        initLayers(MainScene.Layer.COUNT);

        add(Layer.bg1, new HorzScrollBackground(R.mipmap.bg_city, 20));
        add(Layer.bg2, new HorzScrollBackground(R.mipmap.clouds, 40).setModeFullVert(false));
    }
}
