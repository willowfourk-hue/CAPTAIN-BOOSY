package com.grigore.gamebooster;

import android.app.Activity;
import android.os.Bundle;
import android.os.BatteryManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.Display;
import java.util.Locale;

public class MainActivity extends Activity {
    private final int bg = Color.rgb(8, 11, 18);
    private final int panel = Color.rgb(18, 24, 36);
    private final int accent = Color.rgb(45, 130, 255);
    private TextView refreshValue;
    private TextView batteryValue;
    private TextView statusValue;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        getWindow().setStatusBarColor(bg);
        getWindow().setNavigationBarColor(bg);
        getWindow().getDecorView().setSystemUiVisibility(0);
        buildUi();
        updateStats();
    }

    private GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    private TextView text(String value, int size, int color, boolean bold) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setTypeface(Typeface.create("sans-serif", bold ? Typeface.BOLD : Typeface.NORMAL));
        return t;
    }

    private LinearLayout column() {
        LinearLayout l = new LinearLayout(this);
        l.setOrientation(LinearLayout.VERTICAL);
        return l;
    }

    private void buildUi() {
        ScrollView scroll = new ScrollView(this);
        scroll.setBackgroundColor(bg);
        LinearLayout page = column();
        page.setPadding(dp(20), dp(24), dp(20), dp(28));
        scroll.addView(page);

        TextView brand = text("CAPTAIN", 13, accent, true);
        brand.setLetterSpacing(0.22f);
        page.addView(brand);

        TextView title = text("BOOST", 29, Color.WHITE, true);
        title.setPadding(0, dp(5), 0, 0);
        page.addView(title);
        TextView sub = text("DEVICE PERFORMANCE CENTER", 10, Color.rgb(140,153,174), true);
        sub.setLetterSpacing(0.14f);
        sub.setPadding(0, dp(5), 0, dp(22));
        page.addView(sub);

        LinearLayout hero = card();
        TextView live = text("LIVE DISPLAY STATUS", 11, accent, true);
        live.setLetterSpacing(0.1f);
        hero.addView(live);
        refreshValue = text("-- Hz", 38, Color.WHITE, true);
        refreshValue.setPadding(0, dp(8), 0, 0);
        hero.addView(refreshValue);
        TextView hint = text("Current screen refresh rate", 12, Color.rgb(150,162,180), false);
        hero.addView(hint);
        page.addView(hero);

        LinearLayout metrics = new LinearLayout(this);
        metrics.setOrientation(LinearLayout.HORIZONTAL);
        metrics.setPadding(0, dp(12), 0, dp(12));
        LinearLayout battery = card();
        battery.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1));
        batteryValue = text("--", 24, Color.WHITE, true);
        battery.addView(text("BATTERY", 10, accent, true));
        battery.addView(batteryValue);
        battery.addView(text("Battery level", 11, Color.rgb(150,162,180), false));
        LinearLayout thermal = card();
        LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(0, -2, 1);
        tp.setMargins(dp(10), 0, 0, 0);
        thermal.setLayoutParams(tp);
        statusValue = text("Ready", 24, Color.WHITE, true);
        thermal.addView(text("BOOST STATUS", 10, accent, true));
        thermal.addView(statusValue);
        thermal.addView(text("No background service", 11, Color.rgb(150,162,180), false));
        metrics.addView(battery);
        metrics.addView(thermal);
        page.addView(metrics);

        page.addView(section("GAME LAUNCHER"));
        addAction(page, "CALL OF DUTY: MOBILE", "Launch the installed global or Garena version.", v -> launchFirst(
            "com.activision.callofduty.shooter", "com.garena.game.codm"));
        addAction(page, "ROBLOX", "Launch Roblox if it is installed.", v -> launchFirst(
            "com.roblox.client"));

        page.addView(section("QUICK ACTIONS"));
        addAction(page, "DISPLAY SETTINGS", "Choose the highest refresh rate supported by your phone.", v -> open(Settings.ACTION_DISPLAY_SETTINGS));
        addAction(page, "BATTERY SETTINGS", "Review battery and background-usage controls.", v -> open(Settings.ACTION_BATTERY_SAVER_SETTINGS));
        addAction(page, "APP SETTINGS", "Manage permissions and battery restrictions for this app.", v -> openAppDetails());

        page.addView(section("PERFORMANCE NOTES"));
        LinearLayout notes = card();
        notes.addView(text("What this app can do", 15, Color.WHITE, true));
        notes.addView(note("• Read the display refresh rate reported by Android."));
        notes.addView(note("• Open system settings for supported display and battery controls."));
        notes.addView(note("• Keep its own interface responsive and lightweight."));
        notes.addView(text("What it cannot force", 15, Color.WHITE, true));
        TextView limitation = note("• It cannot unlock 120 FPS in a game that does not support it, change another app's frame cap, or guarantee no kicks or bans. Game FPS depends on device hardware, heat, drivers, and the game's own settings.");
        limitation.setPadding(0, dp(8), 0, 0);
        notes.addView(limitation);
        page.addView(notes);

        TextView footer = text("CAPTAIN BOOST  •  BUILD 1.0", 10, Color.rgb(95,108,128), true);
        footer.setGravity(Gravity.CENTER);
        footer.setPadding(0, dp(24), 0, 0);
        page.addView(footer);

        setContentView(scroll);
    }

    private LinearLayout card() {
        LinearLayout c = column();
        c.setPadding(dp(16), dp(16), dp(16), dp(16));
        c.setBackground(shape(panel, dp(18)));
        return c;
    }

    private TextView section(String name) {
        TextView t = text(name, 11, Color.rgb(140,153,174), true);
        t.setLetterSpacing(0.12f);
        t.setPadding(0, dp(18), 0, dp(10));
        return t;
    }

    private TextView note(String value) {
        TextView t = text(value, 12, Color.rgb(170,181,198), false);
        t.setPadding(0, dp(8), 0, dp(3));
        return t;
    }

    private void addAction(LinearLayout parent, String title, String description, View.OnClickListener click) {
        LinearLayout c = card();
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
        p.bottomMargin = dp(9);
        c.setLayoutParams(p);
        c.addView(text(title, 13, Color.WHITE, true));
        TextView d = note(description);
        c.addView(d);
        Button b = new Button(this);
        b.setText("OPEN");
        b.setTextSize(11);
        b.setTextColor(bg);
        b.setAllCaps(true);
        b.setBackground(shape(accent, dp(10)));
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, dp(42));
        bp.topMargin = dp(8);
        c.addView(b, bp);
        b.setOnClickListener(click);
        parent.addView(c);
    }

    private void updateStats() {
        try {
            Display display = getWindowManager().getDefaultDisplay();
            float hz = display.getRefreshRate();
            refreshValue.setText(String.format(Locale.US, "%.0f Hz", hz));
            statusValue.setText("Ready");
        } catch (Exception e) {
            refreshValue.setText("Unavailable");
            statusValue.setText("Limited");
        }
        try {
            BatteryManager bm = (BatteryManager)getSystemService(Context.BATTERY_SERVICE);
            int level = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
            batteryValue.setText(level >= 0 ? level + "%" : "N/A");
        } catch (Exception e) {
            batteryValue.setText("N/A");
        }
    }

    private void launchFirst(String... packages) {
        for (String packageName : packages) {
            try {
                Intent launch = getPackageManager().getLaunchIntentForPackage(packageName);
                if (launch != null) {
                    startActivity(launch);
                    return;
                }
            } catch (Exception ignored) { }
        }
        android.widget.Toast.makeText(this,
            "Game not found. Install it from its official app store first.",
            android.widget.Toast.LENGTH_LONG).show();
    }

    private void open(String action) {
        try { startActivity(new Intent(action)); }
        catch (Exception e) { startActivity(new Intent(Settings.ACTION_SETTINGS)); }
    }

    private void openAppDetails() {
        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.setData(android.net.Uri.parse("package:" + getPackageName()));
        startActivity(i);
    }

    private int dp(int v) { return (int)(v * getResources().getDisplayMetrics().density + 0.5f); }
}
