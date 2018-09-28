package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.ImagePickerAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.bean.TopsBean;
import com.xbdl.xinushop.constant.ImagePickerConstant;
import com.xbdl.xinushop.dialogfragment.FindAllSubjectDialogFragment;
import com.xbdl.xinushop.listener.OnItemAllTopListener;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.ImageUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.view.SelectDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.xbdl.xinushop.MyApplication.maxImgCount;

/**
 * 添加  参与话题
 */
public class AddSubjectActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    AppCompatImageView ivtop;
    AppCompatTextView tvTopmessage;
    AppCompatTextView tvTopdesc;


    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片

    AppCompatEditText etTitle, etMessage;//输入标题 内容
String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();

        findViewById(R.id.iv_addsubject).setOnClickListener(this);
        ivtop = findViewById(R.id.iv_icon);
        tvTopmessage = findViewById(R.id.tv_message);
        tvTopdesc = findViewById(R.id.tv_desc);


        //图片
        RecyclerView recyclerView = findViewById(R.id.rv_subjectimage);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 6);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //图片
        findViewById(R.id.tv_commit).setOnClickListener(this);
        etTitle = findViewById(R.id.et_title);
        etMessage = findViewById(R.id.et_message);
    }

    @Override
    protected Activity getActivity() {
        return AddSubjectActivity.this;
    }

    TopsBean topsBean = null;//话题 bean

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_addsubject:
                FindAllSubjectDialogFragment findAllSubjectDialogFragment =
                        FindAllSubjectDialogFragment.newInstance();
                findAllSubjectDialogFragment.setOnItemAllTopListener(new OnItemAllTopListener() {
                    @Override
                    public void onItemAllTop(TopsBean topsBea) {
                        topsBean = topsBea;
                        findViewById(R.id.ll_toplayout).setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(topsBean.getImg_path()).into(ivtop);
                        tvTopmessage.setText(topsBean.getT_name());
                        tvTopdesc.setText(topsBean.getT_desc());
                    }
                });
                findAllSubjectDialogFragment.show(getSupportFragmentManager(), findAllSubjectDialogFragment.getClass().getName());
                break;
            case R.id.tv_commit:
                if (topsBean == null) {
                    Toast.makeText(getActivity(), "请选择话题", Toast.LENGTH_LONG).show();
                } else if (Judge.getBoolean_isNull(etTitle.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入标题", Toast.LENGTH_LONG).show();
                } else if (Judge.getBoolean_isNull(etMessage.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_LONG).show();
                } else if (adapter.getImages().size() == 0) {
                    Toast.makeText(getActivity(), "请选择图片", Toast.LENGTH_LONG).show();
                } else {
                    //showLoading("");
                  //  showLoading();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < adapter.getImages().size(); i++) {
                        String path = adapter.getImages().get(i).path;
                        String pathbase64 = ImageUtils.bitmapToString(path);
                        if (adapter.getImages().get(i).mimeType.contains("image/jpeg") || adapter.getImages().get(i).mimeType.contains("image/jpg")) {
                            if (adapter.getImages().size() == 1) {
                                sb.append(pathbase64);
                                sb.append(";");
                            } else {
                                sb.append(pathbase64);
                                sb.append(";");
                            }
                        } else {
                            if (adapter.getImages().size() == 1) {
                                sb.append(pathbase64);
                                sb.append(";");
                            } else {
                                sb.append(pathbase64);
                                sb.append(";");
                            }
                        }

                    }
                    String base64images = sb.toString();
                    String topid = topsBean.getTopic_id() + "";
                    String title = etTitle.getText().toString();
                    String message = etMessage.getText().toString();

                    HttpUtils.setPost(token, 3 + "",
                            0 + "", topid + "",
                            0 + "", base64images,
                            title, message, new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    try {
                                        JSONObject jsonObject=new JSONObject(response.body());
                                        int code=jsonObject.getInt("code");
                                        if (code!=100)
                                        {
                                            Toast.makeText(getActivity(),"提交失败",Toast.LENGTH_LONG).show();
                                        }else {
                                            Toast.makeText(getActivity(),"提交成功",Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    } catch (JSONException e) {
                                        Toast.makeText(getActivity(),"提交失败",Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    Toast.makeText(getActivity(),"提交失败",Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFinish() {
                                    super.onFinish();
                                    dismissLoading();
                                }
                            });
                }
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case ImagePickerConstant.IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");

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


    ArrayList<ImageItem> images = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == ImagePickerConstant.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == ImagePickerConstant.REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }
}
