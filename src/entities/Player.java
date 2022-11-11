package entities;

import utilities.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utilities.Constants.Directions.*;
import static utilities.Constants.Directions.DOWN;
import static utilities.Constants.PlayerConstants.*;

public class Player extends Entity{



    private BufferedImage[][] animations;
    private int animationTick=0, animationIndex=0, animationSpeed = 15;
    private int playerAction = IDLE;

    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 3.0f;
    private int[][] levelData;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
    }

    public void update(){
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){

        g.drawImage(animations[playerAction][animationIndex], (int)x, (int)y, width, height, null);
        drawHitbox(g);
    }


    private void updateAnimationTick() {

        animationTick++;
        if(animationTick >= animationSpeed){
            animationTick = 0;
            animationIndex++;
            if(animationIndex >= GetSpriteAmount(playerAction)){
                animationIndex = 0;
                attacking = false;
            }
        }
    }


    private void setAnimation() {

        int startAni = playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
        if(attacking)
            playerAction = ATTACK_1;

        if(startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if(left && !right){
           x -= playerSpeed;
           moving = true;
        } else if(!left && right){
            x += playerSpeed;
            moving = true;
        }
        if(up && !down){
            y -= playerSpeed;
            moving = true;
        } else if(!up && down){
            y += playerSpeed;
            moving = true;
        }
    }

    private void loadAnimations() {

            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[9][6];
            for(int j=0; j<animations.length; j++){
                for(int i=0; i < animations[j].length; i++){
                    animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
                }
            }
    }

    public void loadLevelData(int[][] levelData){
        this.levelData = levelData;

    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
        attacking = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }


}
