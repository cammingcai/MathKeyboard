package com.tianshaokai.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daquexian.flexiblerichtextview.FlexibleRichTextView;
import com.tianshaokai.mathkeyboard.KeyboardFragment;
import com.tianshaokai.mathkeyboard.utils.LatexUtil;

import org.scilab.forge.jlatexmath.core.AjLatexMath;

public class KeyboardActivity extends AppCompatActivity {

    private String[] storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private TextView tvContent;
    private ImageView ivContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keyboard);

        tvContent = (TextView) findViewById(R.id.tvTitle);


        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard(tvContent);
            }
        });


        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(this, storagePermission[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                ActivityCompat.requestPermissions(this, storagePermission, 100);
            } else {
                LatexUtil.init(this);
            }
        } else {
            LatexUtil.init(this);
        }


        AjLatexMath.init(this);

        FlexibleRichTextView richTextView = (FlexibleRichTextView) findViewById(R.id.test_text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("$$\\sum_{i=1}^n a_i=0$$,");

        stringBuilder.append("$$f(x)=x^{x^x}$$");
        stringBuilder.append("$$f(x_1,x_x,\\ldots,x_n) = x_1^2 + x_2^2 + \\cdots + x_n^2 $$");
        stringBuilder.append("$$\\left. \\frac{du}{dx} \\right|_{x=0}.$$");
        stringBuilder.append("f(n) = \\begin{cases} \\frac{n}{2}, & \\text{if } n\\text{ is even} \\\\ 3n+1, & \\text{if } n\\text{ is odd} \\end{cases}");

        stringBuilder.append("$$\\mbox{对任意的$x>0$}, \\mbox{有 }f(x)>0. $$");
        stringBuilder.append("$$\\sqrt[n]{x_r_r_r} $$");
        stringBuilder.append("$$ \\frac{x+2}{x} \\sqrt{x} $$");
        stringBuilder.append("$$ \\[f(x,y,z) = 3y^2 z \\left( 3 + \\frac{7x+5}{1 + y^2} \\right).\\] $$");

        stringBuilder.append("$$ P(x|c)=\\frac{P(c|x)\\cdot P(x)}{P(x)} $$");
        stringBuilder.append("$$ \\Large x=\\frac{-b\\pm\\sqrt{b^2-4ac}}{2a} $$");
        stringBuilder.append("$$ \\sum_{i=1}^n i = \\frac{n(n+1)}2 $$");
        stringBuilder.append("$$ f(x)=\\int_{-\\infty}^x e^{-t^2}dt $$ 这道公式我也不知道怎么做");

        stringBuilder.append("$$ \\cos 2\\theta  = \\cos^2 \\theta - \\sin^2 \\theta = 2 \\cos^2 \\theta - 1. $$");

        stringBuilder.append("$$ \\displaystyle= \\frac{k(k+1)}{2}+k+1 $$");
        stringBuilder.append("$$ \\frac{x}{2}-3=0 $$");
        stringBuilder.append("$$ x=\\frac{3}{2} $$");
        stringBuilder.append("$$ \\[ \\sum_{k=1}^n k^2 = \\frac{1}{2} n (n+1).\\] $$");
        stringBuilder.append("$$ q(x,t)= \\begin{cases}(t-k+1)x^2,\\quad \\ \\ & t\\in\\big(k-1,k-\\dfrac{1}{2}\\big]\\\\ (k-t)x^2, \\quad \\ \\ & t\\in\\big(k-\\dfrac{1}{2},k\\big]\\end{cases}  $$");

        richTextView.setText(stringBuilder.toString());
    }
//    private void setformula() {
//        String content = "$$\\[ \\sum_{k=1}^n k^2 = \\frac{1}{2} n (n+1).\\]$$";
//        int w = getResources().getDisplayMetrics().widthPixels;
//        int h = getResources().getDisplayMetrics().heightPixels;
//        TeXFormula formula = new TeXFormula(content);
//        TeXIcon icon = formula.new TeXIconBuilder()
//                .setStyle(TeXConstants.STYLE_DISPLAY)
//                .setSize(30)
//                .setWidth(TeXConstants.UNIT_PIXEL, w, TeXConstants.ALIGN_LEFT)
//                .setIsMaxWidth(true)
//                .setInterLineSpacing(TeXConstants.UNIT_PIXEL,
//                        AjLatexMath.getLeading(30)).build();
//        icon.setInsets(new Insets(5, 5, 5, 5));
//
//        Bitmap image = Bitmap.createBitmap(icon.getIconWidth(), icon.getIconHeight(),
//                Bitmap.Config.ARGB_8888);
//
//        Canvas g2 = new Canvas(image);
//        g2.drawColor(Color.WHITE);
//        icon.paintIcon(g2, 0, 0);
//
//        Bitmap scaleimage = scaleBitmapAndKeepRation(image, h, w);
//
//        ivContent.setImageBitmap(scaleimage);
//    }
//    public Bitmap scaleBitmapAndKeepRation(Bitmap targetBmp,
//                                           int reqHeightInPixels, int reqWidthInPixels) {
//        Bitmap bitmap = Bitmap.createBitmap(reqWidthInPixels,
//                reqHeightInPixels, Bitmap.Config.ARGB_8888);
//        Canvas g = new Canvas(bitmap);
//        g.drawBitmap(targetBmp, 0, 0, null);
//        targetBmp.recycle();
//        return bitmap;
//    }
    private void showKeyboard(TextView textView) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(KeyboardFragment.TAG);
        if (fragment != null) {
            //为了不重复显示dialog，在显示对话框之前移除正在显示的对话框
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        final KeyboardFragment keyboardFragment = new KeyboardFragment();
        keyboardFragment.setOutSide(textView);
        fragmentManager.beginTransaction().add(keyboardFragment, KeyboardFragment.TAG).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                    boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                    if (!b) {
                        // 用户还是想用我的 APP 的
                        // 提示用户去应用设置界面手动开启权限
                        Toast.makeText(this, "需要开启权限", Toast.LENGTH_SHORT).show();
                    } else {
                        finish();
                    }
                } else {
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();

                    LatexUtil.init(this);
                }
            }
        }
    }
}
