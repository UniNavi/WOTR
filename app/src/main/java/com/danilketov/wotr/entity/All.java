package com.danilketov.wotr.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class All {

    @PrimaryKey
    @SerializedName("max_xp")
    private int maxXp;
    private int battles;
    @SerializedName("avg_damage_assisted")
    private float avgDamageAssisted;
    @SerializedName("avg_damage_blocked")
    private float avgDamageBlocked;
    @SerializedName("battle_avg_xp")
    private int battleAvgXp;
    @SerializedName("hits_percents")
    private int hitsPercents;
    @SerializedName("max_damage")
    private int maxDamage;
    @SerializedName("survived_battles")
    private int survivedBattles;
    private int wins;
    private int losses;
    private int draws;
    private int shots;
    private int frags;
    private int hits;
    private int spotted;
    private int xp;
    @SerializedName("capture_points")
    private int capturePoints;
    @SerializedName("damage_dealt")
    private int damageDealt;
    @SerializedName("damage_received")
    private int damageReceived;
    @SerializedName("direct_hits_received")
    private int directHitsReceived;
    @SerializedName("dropped_capture_points")
    private int droppedCapturePoints;
    @SerializedName("avg_damage_assisted_radio")
    private float avgDamageAssistedRadio;
    @SerializedName("avg_damage_assisted_track")
    private float avgDamageAssistedTrack;

    public All(int maxXp, int battles, float avgDamageAssisted, float avgDamageBlocked, int battleAvgXp, int hitsPercents, int maxDamage, int survivedBattles, int wins, int losses, int draws, int shots, int frags, int hits, int spotted, int xp, int capturePoints, int damageDealt, int damageReceived, int directHitsReceived, int droppedCapturePoints, float avgDamageAssistedRadio, float avgDamageAssistedTrack) {
        this.maxXp = maxXp;
        this.battles = battles;
        this.avgDamageAssisted = avgDamageAssisted;
        this.avgDamageBlocked = avgDamageBlocked;
        this.battleAvgXp = battleAvgXp;
        this.hitsPercents = hitsPercents;
        this.maxDamage = maxDamage;
        this.survivedBattles = survivedBattles;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.shots = shots;
        this.frags = frags;
        this.hits = hits;
        this.spotted = spotted;
        this.xp = xp;
        this.capturePoints = capturePoints;
        this.damageDealt = damageDealt;
        this.damageReceived = damageReceived;
        this.directHitsReceived = directHitsReceived;
        this.droppedCapturePoints = droppedCapturePoints;
        this.avgDamageAssistedRadio = avgDamageAssistedRadio;
        this.avgDamageAssistedTrack = avgDamageAssistedTrack;
    }

    public int getMaxXp() {
        return maxXp;
    }

    public int getBattles() {
        return battles;
    }

    public float getAvgDamageAssisted() {
        return avgDamageAssisted;
    }

    public float getAvgDamageBlocked() {
        return avgDamageBlocked;
    }

    public int getBattleAvgXp() {
        return battleAvgXp;
    }

    public int getHitsPercents() {
        return hitsPercents;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public int getSurvivedBattles() {
        return survivedBattles;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getDraws() {
        return draws;
    }

    public int getShots() {
        return shots;
    }

    public int getFrags() {
        return frags;
    }

    public int getHits() {
        return hits;
    }

    public int getSpotted() {
        return spotted;
    }

    public int getXp() {
        return xp;
    }

    public int getCapturePoints() {
        return capturePoints;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public int getDamageReceived() {
        return damageReceived;
    }

    public int getDirectHitsReceived() {
        return directHitsReceived;
    }

    public int getDroppedCapturePoints() {
        return droppedCapturePoints;
    }

    public float getAvgDamageAssistedRadio() {
        return avgDamageAssistedRadio;
    }

    public float getAvgDamageAssistedTrack() {
        return avgDamageAssistedTrack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        All all = (All) o;

        if (maxXp != all.maxXp) return false;
        if (battles != all.battles) return false;
        if (Float.compare(all.avgDamageAssisted, avgDamageAssisted) != 0) return false;
        if (Float.compare(all.avgDamageBlocked, avgDamageBlocked) != 0) return false;
        if (battleAvgXp != all.battleAvgXp) return false;
        if (hitsPercents != all.hitsPercents) return false;
        if (maxDamage != all.maxDamage) return false;
        if (survivedBattles != all.survivedBattles) return false;
        if (wins != all.wins) return false;
        if (losses != all.losses) return false;
        if (draws != all.draws) return false;
        if (shots != all.shots) return false;
        if (frags != all.frags) return false;
        if (hits != all.hits) return false;
        if (spotted != all.spotted) return false;
        if (xp != all.xp) return false;
        if (capturePoints != all.capturePoints) return false;
        if (damageDealt != all.damageDealt) return false;
        if (damageReceived != all.damageReceived) return false;
        if (directHitsReceived != all.directHitsReceived) return false;
        if (droppedCapturePoints != all.droppedCapturePoints) return false;
        if (Float.compare(all.avgDamageAssistedRadio, avgDamageAssistedRadio) != 0)
            return false;
        return Float.compare(all.avgDamageAssistedTrack, avgDamageAssistedTrack) == 0;
    }

    @Override
    public int hashCode() {
        int result = maxXp;
        result = 31 * result + battles;
        result = 31 * result + (avgDamageAssisted != +0.0f ? Float.floatToIntBits(avgDamageAssisted) : 0);
        result = 31 * result + (avgDamageBlocked != +0.0f ? Float.floatToIntBits(avgDamageBlocked) : 0);
        result = 31 * result + battleAvgXp;
        result = 31 * result + hitsPercents;
        result = 31 * result + maxDamage;
        result = 31 * result + survivedBattles;
        result = 31 * result + wins;
        result = 31 * result + losses;
        result = 31 * result + draws;
        result = 31 * result + shots;
        result = 31 * result + frags;
        result = 31 * result + hits;
        result = 31 * result + spotted;
        result = 31 * result + xp;
        result = 31 * result + capturePoints;
        result = 31 * result + damageDealt;
        result = 31 * result + damageReceived;
        result = 31 * result + directHitsReceived;
        result = 31 * result + droppedCapturePoints;
        result = 31 * result + (avgDamageAssistedRadio != +0.0f ? Float.floatToIntBits(avgDamageAssistedRadio) : 0);
        result = 31 * result + (avgDamageAssistedTrack != +0.0f ? Float.floatToIntBits(avgDamageAssistedTrack) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "All{" +
                "maxXp=" + maxXp +
                ", battles=" + battles +
                ", avgDamageAssisted=" + avgDamageAssisted +
                ", avgDamageBlocked=" + avgDamageBlocked +
                ", battleAvgXp=" + battleAvgXp +
                ", hitsPercents=" + hitsPercents +
                ", maxDamage=" + maxDamage +
                ", survivedBattles=" + survivedBattles +
                ", wins=" + wins +
                ", losses=" + losses +
                ", draws=" + draws +
                ", shots=" + shots +
                ", frags=" + frags +
                ", hits=" + hits +
                ", spotted=" + spotted +
                ", xp=" + xp +
                ", capturePoints=" + capturePoints +
                ", damageDealt=" + damageDealt +
                ", damageReceived=" + damageReceived +
                ", directHitsReceived=" + directHitsReceived +
                ", droppedCapturePoints=" + droppedCapturePoints +
                ", avgDamageAssistedRadio=" + avgDamageAssistedRadio +
                ", avgDamageAssistedTrack=" + avgDamageAssistedTrack +
                '}';
    }
}
