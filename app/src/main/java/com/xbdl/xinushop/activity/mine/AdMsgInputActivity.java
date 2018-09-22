package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.AddCategoryBeanAdapter;
import com.xbdl.xinushop.adapter.ImagePickerAdapter;
import com.xbdl.xinushop.bean.CategoryBean;
import com.xbdl.xinushop.constant.ImagePickerConstant;
import com.xbdl.xinushop.dialogfragment.SettingBackgroundDialogFragment;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.view.SelectDialog;

import java.util.ArrayList;
import java.util.List;

import static com.xbdl.xinushop.MyApplication.maxImgCount;

/*
 * 填写广告信息
 * */
public class AdMsgInputActivity extends AppCompatActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener, View.OnClickListener, SettingBackgroundDialogFragment.OnPhotoLinstener {
    AppCompatTextView tvTrading;
    AppCompatTextView tvGoodscategory;//商品分类

    //属性类别
    AppCompatEditText etAttributecategory;//类别
    AppCompatEditText etAttributeprice;//价格
    AppCompatEditText etAttributestock;//库存
    AppCompatTextView tvAddAttribute;
    AddCategoryBeanAdapter addCategoryBeanAdapter;
    List<CategoryBean> categoryBeans;
    //属性类别

    AppCompatImageView ivFront, ivReverse, ivBusiness;//正面  反面  营业执照副本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_msg_input);
        initView();
        initGoodsCategory();
    }

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片

    private void initView() {
        //添加商品图片
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.shopimages);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 6);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //添加商品图片
        tvTrading = findViewById(R.id.tv_trading);
        tvTrading.setOnClickListener(this);//交易方式
        tvGoodscategory = findViewById(R.id.tv_goodscategory); //商品分类
        tvGoodscategory.setClickable(false);
        tvGoodscategory.setOnClickListener(this);

        //属性类别
        etAttributecategory = findViewById(R.id.et_attributecategory);
        etAttributeprice = findViewById(R.id.et_attributeprice);
        etAttributestock = findViewById(R.id.et_attributestock);
        tvAddAttribute = findViewById(R.id.tv_addattribute);
        tvAddAttribute.setOnClickListener(this);
        RecyclerView rvCategory = findViewById(R.id.rv_category);
        rvCategory.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        addCategoryBeanAdapter = new AddCategoryBeanAdapter(getActivity());
        rvCategory.setAdapter(addCategoryBeanAdapter);
        categoryBeans = new ArrayList<>();
        //属性类别
        ivFront = findViewById(R.id.iv_front);
        ivReverse = findViewById(R.id.iv_reverse);
        ivBusiness = findViewById(R.id.iv_business);
        ivFront.setOnClickListener(this);
        ivReverse.setOnClickListener(this);
        ivBusiness.setOnClickListener(this);
    }

    /**
     * 获取商品分类
     */
    private void initGoodsCategory() {
      /*  HttpUtils.getGoodsCategory(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                Log.i("asdf", "getGoodsCategory" + response.body());
            }
        });*/
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case ImagePickerConstant.IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                type = 0;
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(6 - selImageList.size());
                                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, ImagePickerConstant.REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, ImagePickerConstant.REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(getActivity(), ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
//                intentPreview.putExtra(ImagePicker.RightImageIsShow, false);
                startActivityForResult(intentPreview, ImagePickerConstant.REQUEST_CODE_PREVIEW);
                break;
        }
    }


    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == ImagePickerConstant.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    if (type == 0) {
                        selImageList.addAll(images);
                        adapter.setImages(selImageList);
                    } else if (type == 1) {
                        Glide.with(getActivity()).load(images.get(0).path).into(ivFront);
                    } else if (type == 2) {
                        Glide.with(getActivity()).load(images.get(0).path).into(ivReverse);
                    } else if (type == 3) {
                        Glide.with(getActivity()).load(images.get(0).path).into(ivBusiness);
                    }

                }
            }
        }
//        else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == ImagePickerConstant.REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images);
//                    adapter.setImages(selImageList);
//                }
//            }
//        }
    }

    /**
     * this
     *
     * @return
     */
    private Activity getActivity() {
        return AdMsgInputActivity.this;
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            maxImgCount = 6;
            dialog.show();
        }
        return dialog;
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_trading: {
                final String[] items = new String[]{"喜绿商品", "外部商品"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.myDialog2);
                builder.setTitle("请选择交易方式");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        tvTrading.setText(items[i]);
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                break;
            }
            case R.id.tv_goodscategory: {

                break;
            }
            //添加属性类别
            case R.id.tv_addattribute: {
                if (Judge.getBoolean_isNull(etAttributecategory.getText().toString())) {
                    Toast.makeText(getActivity(), "请填入类别", Toast.LENGTH_LONG).show();
                } else if (Judge.getBoolean_isNull(etAttributeprice.getText().toString())) {
                    Toast.makeText(getActivity(), "请填入价格", Toast.LENGTH_LONG).show();
                } else if (Judge.getBoolean_isNull(etAttributestock.getText().toString())) {
                    Toast.makeText(getActivity(), "请填入库存", Toast.LENGTH_LONG).show();
                } else {
                    CategoryBean categoryBean = new CategoryBean(
                            etAttributecategory.getText().toString()
                            , etAttributeprice.getText().toString(),
                            etAttributestock.getText().toString());
                    categoryBeans.add(categoryBean);
                    addCategoryBeanAdapter.refreshData(categoryBeans);
                }
                break;
            }
            case R.id.iv_front: {
                maxImgCount = 1;
                type = 1;
                SettingBackgroundDialogFragment settingBackgroundDialogFragment = SettingBackgroundDialogFragment.newInstance();
                settingBackgroundDialogFragment.show(getSupportFragmentManager(), settingBackgroundDialogFragment.getClass().getName());
                break;
            }
            case R.id.iv_reverse: {
                maxImgCount = 1;
                type = 2;
                SettingBackgroundDialogFragment settingBackgroundDialogFragment = SettingBackgroundDialogFragment.newInstance();
                settingBackgroundDialogFragment.show(getSupportFragmentManager(), settingBackgroundDialogFragment.getClass().getName());
                break;
            }
            case R.id.iv_business: {
                maxImgCount = 1;
                type = 3;
                SettingBackgroundDialogFragment settingBackgroundDialogFragment = SettingBackgroundDialogFragment.newInstance();
                settingBackgroundDialogFragment.show(getSupportFragmentManager(), settingBackgroundDialogFragment.getClass().getName());
                break;
            }
        }
    }

    int type = 0;//0 商品图片  1正面  2反面  3营业执照

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("getGoodsCategory");
    }

    @Override
    public void takePhoto(View view) {
        switch (view.getId()) {
            case R.id.tv_takephoto:
                /**
                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                 *
                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                 *
                 * 如果实在有所需要，请直接下载源码引用。
                 */
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, ImagePickerConstant.REQUEST_CODE_SELECT);
                break;

            case R.id.tv_photoview:
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(getActivity(), ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                startActivityForResult(intent1, ImagePickerConstant.REQUEST_CODE_SELECT);
                break;

        }


    }
}
