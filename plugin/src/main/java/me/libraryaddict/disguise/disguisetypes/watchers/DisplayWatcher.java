package me.libraryaddict.disguise.disguisetypes.watchers;

import com.github.retrooper.packetevents.util.Quaternion4f;
import com.github.retrooper.packetevents.util.Vector3f;
import me.libraryaddict.disguise.disguisetypes.Disguise;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MetaIndex;
import me.libraryaddict.disguise.utilities.parser.RandomDefaultValue;
import org.bukkit.Color;
import org.bukkit.entity.Display;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;

public abstract class DisplayWatcher extends FlagWatcher {
    public DisplayWatcher(Disguise disguise) {
        super(disguise);
    }

    public Transformation getTransformation() {
        return new Transformation(getTranslation(), getLeftRotation(), getScale(), getRightRotation());
    }

    // Because BlockDisplayWatcher modifies this on startup..
    @RandomDefaultValue
    public void setTransformation(Transformation transformation) {
        org.joml.Vector3f trans = transformation.getTranslation();
        Quaternionf rl = transformation.getLeftRotation();
        Quaternionf rr = transformation.getRightRotation();
        org.joml.Vector3f scale = transformation.getScale();

        setData(MetaIndex.DISPLAY_TRANSLATION, new Vector3f(trans.x, trans.y, trans.z));
        setData(MetaIndex.DISPLAY_LEFT_ROTATION, new Quaternion4f(rl.x, rl.y, rl.z, rl.w));
        setData(MetaIndex.DISPLAY_RIGHT_ROTATION, new Quaternion4f(rr.x, rr.y, rr.z, rr.w));
        setData(MetaIndex.DISPLAY_SCALE, new Vector3f(scale.x, scale.y, scale.z));

        sendData(MetaIndex.DISPLAY_TRANSLATION, MetaIndex.DISPLAY_LEFT_ROTATION, MetaIndex.DISPLAY_RIGHT_ROTATION, MetaIndex.DISPLAY_SCALE);
    }

    public org.joml.Vector3f getTranslation() {
        Vector3f vec = getData(MetaIndex.DISPLAY_TRANSLATION);

        return new org.joml.Vector3f(vec.x, vec.y, vec.z);
    }

    // Because BlockDisplayWatcher modifies this on startup..
    @RandomDefaultValue
    public void setTranslation(org.joml.Vector3f translation) {
        setData(MetaIndex.DISPLAY_TRANSLATION, new Vector3f(translation.x, translation.y, translation.z));
        sendData(MetaIndex.DISPLAY_TRANSLATION);
    }

    public org.joml.Vector3f getScale() {
        Vector3f vec = getData(MetaIndex.DISPLAY_SCALE);

        return new org.joml.Vector3f(vec.x, vec.y, vec.z);
    }

    public void setScale(org.joml.Vector3f scale) {
        setData(MetaIndex.DISPLAY_SCALE, new Vector3f(scale.x, scale.y, scale.z));
        sendData(MetaIndex.DISPLAY_SCALE);
    }

    public Quaternionf getLeftRotation() {
        Quaternion4f rot = getData(MetaIndex.DISPLAY_LEFT_ROTATION);

        return new Quaternionf(rot.getX(), rot.getY(), rot.getZ(), rot.getW());
    }

    public void setLeftRotation(Quaternionf rotation) {
        setData(MetaIndex.DISPLAY_LEFT_ROTATION, new Quaternion4f(rotation.x, rotation.y, rotation.z, rotation.w));
        sendData(MetaIndex.DISPLAY_LEFT_ROTATION);
    }

    public Quaternionf getRightRotation() {
        Quaternion4f rot = getData(MetaIndex.DISPLAY_RIGHT_ROTATION);

        return new Quaternionf(rot.getX(), rot.getY(), rot.getZ(), rot.getW());
    }

    public void setRightRotation(Quaternionf rotation) {
        setData(MetaIndex.DISPLAY_RIGHT_ROTATION, new Quaternion4f(rotation.x, rotation.y, rotation.z, rotation.w));
        sendData(MetaIndex.DISPLAY_RIGHT_ROTATION);
    }

    public int getInterpolationDuration() {
        return getData(MetaIndex.DISPLAY_INTERPOLATION_DURATION);
    }

    public void setInterpolationDuration(int duration) {
        setData(MetaIndex.DISPLAY_INTERPOLATION_DURATION, duration);
        sendData(MetaIndex.DISPLAY_INTERPOLATION_DURATION);
    }

    public float getViewRange() {
        return getData(MetaIndex.DISPLAY_VIEW_RANGE);
    }

    public void setViewRange(float range) {
        setData(MetaIndex.DISPLAY_VIEW_RANGE, range);
        sendData(MetaIndex.DISPLAY_VIEW_RANGE);
    }

    public float getShadowRadius() {
        return getData(MetaIndex.DISPLAY_SHADOW_RADIUS);
    }

    public void setShadowRadius(float radius) {
        setData(MetaIndex.DISPLAY_SHADOW_RADIUS, radius);
        sendData(MetaIndex.DISPLAY_SHADOW_RADIUS);
    }

    public float getShadowStrength() {
        return getData(MetaIndex.DISPLAY_SHADOW_STRENGTH);
    }

    public void setShadowStrength(float strength) {
        setData(MetaIndex.DISPLAY_SHADOW_STRENGTH, strength);
        sendData(MetaIndex.DISPLAY_SHADOW_STRENGTH);
    }

    public float getDisplayWidth() {
        return getData(MetaIndex.DISPLAY_WIDTH);
    }

    public void setDisplayWidth(float width) {
        setData(MetaIndex.DISPLAY_WIDTH, width);
        sendData(MetaIndex.DISPLAY_WIDTH);
    }

    public float getDisplayHeight() {
        return getData(MetaIndex.DISPLAY_HEIGHT);
    }

    public void setDisplayHeight(float height) {
        setData(MetaIndex.DISPLAY_HEIGHT, height);
        sendData(MetaIndex.DISPLAY_HEIGHT);
    }

    public int getInterpolationDelay() {
        return getData(MetaIndex.DISPLAY_INTERPOLATION_START_DELTA_TICKS);
    }

    public void setInterpolationDelay(int ticks) {
        setData(MetaIndex.DISPLAY_INTERPOLATION_START_DELTA_TICKS, ticks);
        sendData(MetaIndex.DISPLAY_INTERPOLATION_START_DELTA_TICKS);
    }

    public Display.Billboard getBillboard() {
        return Display.Billboard.values()[getData(MetaIndex.DISPLAY_BILLBOARD_RENDER_CONSTRAINTS)];
    }

    // Because TextDisplayWatcher modifies this on startup..
    @RandomDefaultValue
    public void setBillboard(Display.Billboard billboard) {
        setData(MetaIndex.DISPLAY_BILLBOARD_RENDER_CONSTRAINTS, (byte) billboard.ordinal());
        sendData(MetaIndex.DISPLAY_BILLBOARD_RENDER_CONSTRAINTS);
    }

    public Color getGlowColorOverride() {
        int color = getData(MetaIndex.DISPLAY_GLOW_COLOR_OVERRIDE);
        return color == -1 ? null : Color.fromARGB(color);
    }

    public void setGlowColorOverride(Color color) {
        setData(MetaIndex.DISPLAY_GLOW_COLOR_OVERRIDE, color == null ? -1 : color.asARGB());
        sendData(MetaIndex.DISPLAY_GLOW_COLOR_OVERRIDE);
    }

    public Display.Brightness getBrightness() {
        int data = getData(MetaIndex.DISPLAY_BRIGHTNESS_OVERRIDE);

        if (data == -1) {
            return null;
        }

        int blockLight = data >> 4 & '\uffff';
        int skyLight = data >> 20 & '\uffff';

        return new Display.Brightness(blockLight, skyLight);
    }

    public void setBrightness(Display.Brightness brightness) {
        setData(MetaIndex.DISPLAY_BRIGHTNESS_OVERRIDE,
            brightness == null ? -1 : brightness.getBlockLight() << 4 | brightness.getSkyLight() << 20);
        sendData(MetaIndex.DISPLAY_BRIGHTNESS_OVERRIDE);
    }

    public int getTeleportDuration() {
        return getData(MetaIndex.DISPLAY_POS_ROT_INTERPOLATION_DURATION);
    }

    public void setTeleportDuration(int duration) {
        setData(MetaIndex.DISPLAY_POS_ROT_INTERPOLATION_DURATION, Math.max(0, Math.min(59, duration)));
        sendData(MetaIndex.DISPLAY_POS_ROT_INTERPOLATION_DURATION);
    }
}
