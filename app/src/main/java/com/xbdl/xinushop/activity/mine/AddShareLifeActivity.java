package com.xbdl.xinushop.activity.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.xbdl.xinushop.R;
import com.xbdl.xinushop.adapter.AllTagAdapter;
import com.xbdl.xinushop.adapter.ImagePickerAdapter;
import com.xbdl.xinushop.adapter.baseadapter.BaseAdapter;
import com.xbdl.xinushop.base.BaseActivity;
import com.xbdl.xinushop.bean.AllTagBean;
import com.xbdl.xinushop.bean.MyConstants;
import com.xbdl.xinushop.bean.PersonBean;
import com.xbdl.xinushop.constant.ImagePickerConstant;
import com.xbdl.xinushop.layoutmanager.FlowLayoutManager;
import com.xbdl.xinushop.utils.HttpUtils;
import com.xbdl.xinushop.utils.ImageUtils;
import com.xbdl.xinushop.utils.Judge;
import com.xbdl.xinushop.utils.SharedPreferencesUtil;
import com.xbdl.xinushop.view.SelectDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.xbdl.xinushop.MyApplication.maxImgCount;

/**
 * 添加分享生活
 */
public class AddShareLifeActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener, View.OnClickListener {
    protected Activity getActivity() {
        return AddShareLifeActivity.this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_share_life);
        initView();
        String userjson = SharedPreferencesUtil.getString(getActivity(), MyConstants.User, "");

        PersonBean personBean = new Gson().fromJson(userjson, PersonBean.class);
        token = personBean.getLoginToken();
        initAllTag(token);

    }
    String token = "";
    AllTagAdapter allTagAdapter;
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片

    AppCompatEditText etTitle, etMessage;//输入标题 内容

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv_tag);
        recyclerView.setLayoutManager(new FlowLayoutManager());
        allTagAdapter = new AllTagAdapter(getActivity());
        recyclerView.setAdapter(allTagAdapter);

        allTagAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                allTagAdapter.setItemCheck(allTagAdapter.getDatas().get(position));
            }
        });

        //图片
        RecyclerView rvAddsharelife = findViewById(R.id.rv_addsharelife);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, 6);
        adapter.setOnItemClickListener(this);

        rvAddsharelife.setLayoutManager(new GridLayoutManager(this, 4));
        rvAddsharelife.setHasFixedSize(true);
        rvAddsharelife.setAdapter(adapter);
        //图片
        findViewById(R.id.tv_commit).setOnClickListener(this);

        etTitle = findViewById(R.id.et_title);
        etMessage = findViewById(R.id.et_message);
    }

    /**
     * 获取所有的tag
     */
    private void initAllTag(String token) {
        HttpUtils.findAllTag(token, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());

                    String taglist = jsonObject.getString("tagList");
                    List<AllTagBean> allTagBeans = getAllTagList(taglist);
                    if (allTagBeans != null && allTagBeans.size() > 0) {
                        allTagAdapter.refreshData(allTagBeans);
                    }
                } catch (JSONException e) {
                }
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
            }

            @Override
            public void onFinish() {
                super.onFinish();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag("findAllTag");
        OkGo.getInstance().cancelTag("setPost");
    }

    /**
     * 解析json
     *
     * @param data
     * @return
     */
    private List<AllTagBean> getAllTagList(String data) {
        if (Judge.getBoolean_isNull(data)) {
            return null;
        }
        try {
            JSONArray array = new JSONArray(data);
            List<AllTagBean> datas = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                AllTagBean allTagBean = new Gson().fromJson(array.getString(i), AllTagBean.class);
                datas.add(allTagBean);
            }
            return datas;
        } catch (JSONException e) {
            return null;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit: {
                if (allTagAdapter.getCheckListSize() == 0) {
                    Toast.makeText(getActivity(), "请选择标签", Toast.LENGTH_LONG).show();
                } else if (Judge.getBoolean_isNull(etTitle.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入标题", Toast.LENGTH_LONG).show();
                } else if (Judge.getBoolean_isNull(etMessage.getText().toString())) {
                    Toast.makeText(getActivity(), "请输入内容", Toast.LENGTH_LONG).show();
                } else if (adapter.getImages().size() == 0) {
                    Toast.makeText(getActivity(), "请选择图片", Toast.LENGTH_LONG).show();
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < adapter.getImages().size(); i++) {
                        String path = adapter.getImages().get(i).path;
                        String pathbase64 = ImageUtils.bitmapToString(path);
                      if (adapter.getImages().get(i).mimeType.contains("image/jpeg")||adapter.getImages().get(i).mimeType.contains("image/jpg"))
                      {
                          pathbase64="data:image/jpeg;base64," +
                                  ""+pathbase64;
                      }else
                      {
                          pathbase64="data:image/png;base64," +
                                  ""+pathbase64;
                      }
                        if (adapter.getImages().size() == 1) {
                            sb.append(pathbase64);
                            sb.append(";");
                        } else {
                            sb.append(pathbase64);
                            sb.append(";");
                        }
                    }
                    String base64images = sb.toString();
                    int tagid = allTagAdapter.getDatas().get(0).getTagId();
                    String title = etTitle.getText().toString();
                    String message = etMessage.getText().toString();

                  HttpUtils.setPost(token, 2 + "",
                          tagid + "", 0 + "",
                          0 + "", base64images,
                          title, message, new StringCallback() {
                              @Override
                              public void onSuccess(Response<String> response) {
                                  Log.i("asdf","onSuccess"+response.body());
                              }

                              @Override
                              public void onError(Response<String> response) {
                                  super.onError(response);
                                  Log.i("asdf","onError"+response.body());
                              }

                              @Override
                              public void onFinish() {
                                  super.onFinish();
                                  Log.i("asdf","onFinish");
                              }
                          });


                }
                break;
            }
        }
    }

}
