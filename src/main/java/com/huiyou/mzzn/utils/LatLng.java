package com.huiyou.mzzn.utils;

public final class LatLng {
	
    public final double latitude;
    public final double longitude;

    public LatLng(double var1, double var3) {
        this(var1, var3, true);
    }

    public LatLng(double var1, double var3, boolean var5) {
        if(!var5) {
            this.latitude = var1;
            this.longitude = var3;
        } else {
            if(-180.0D <= var3 && var3 < 180.0D) {
                this.longitude = var3;
            } else {
                this.longitude = ((var3 - 180.0D) % 360.0D + 360.0D) % 360.0D - 180.0D;
            }

            if(var1 < -90.0D || var1 > 90.0D) {
                try {
                    throw new Exception("非法坐标值");
                } catch (Exception var6) {
                    var6.printStackTrace();
                }
            }

            this.latitude = Math.max(-90.0D, Math.min(90.0D, var1));
        }
    }

    public final LatLng clone() {
        return new LatLng(this.latitude, this.longitude);
    }

    public final int hashCode() {
        long var2 = Double.doubleToLongBits(this.latitude);
        int var1 = 31 + (int)(var2 ^ var2 >>> 32);
        var2 = Double.doubleToLongBits(this.longitude);
        return 31 * var1 + (int)(var2 ^ var2 >>> 32);
    }

    public final boolean equals(Object var1) {
        if(this == var1) {
            return true;
        } else if(!(var1 instanceof LatLng)) {
            return false;
        } else {
            LatLng var2 = (LatLng)var1;
            return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(var2.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(var2.longitude);
        }
    }

    public final String toString() {
        return "lat/lng: (" + this.latitude + "," + this.longitude + ")";
    }

}