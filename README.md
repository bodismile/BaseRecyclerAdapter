# MultipleRecyclerView

## 特点：

- 支持自定义item布局；
- 支持设置多个headerview；
- 支持添加点击、长按事件；
- 使用简单方便。

## 快速入门

### 创建Adapter

	public class ChatAdapter extends BaseRecyclerAdapter<Message> {
	
	    public ChatAdapter(Context context, IMutlipleItem<Message> items, Collection<Message> datas) {
	        super(context,items,datas);
	    }
	
	    @Override
	    public void bindView(BaseRecyclerHolder holder, Message msg, int position) {
	        if(holder.layoutId==R.layout.item_text){
	            holder.setText(R.id.tv_recent_name,msg==null?"未知":msg.getContent()+","+msg.getType());
	        }else if(holder.layoutId==R.layout.item_voice){
	            holder.setText(R.id.tv_recent_name,msg==null?"未知":msg.getContent()+","+msg.getType());
	        }else{
	            holder.setText(R.id.tv_recent_name,msg==null?"未知":msg.getContent()+","+msg.getType());
	        }
	    }
	}


### 支持自定义item布局

	private final int TYPE_TXT=0;
    private final int TYPE_VOICE=1;
    private final int TYPE_OTHER=2;

    IMutlipleItem<Message> mutlipleItem = new IMutlipleItem<Message>() {

        @Override
        public int getItemViewType(int postion, Message msg) {
            if(msg.getType().equals("txt")){
                return TYPE_TXT;
            }else if(msg.getType().equals("voice")){
                return TYPE_VOICE;
            }else{
                return TYPE_OTHER;
            }
        }

        @Override
        public int getItemLayoutId(int viewtype) {
            if(viewtype==TYPE_TXT){
                return R.layout.item_text;
            }else if(viewtype==TYPE_VOICE){
                return R.layout.item_voice;
            }else{
                return R.layout.item_other;
            }
        }

        @Override
        public int getItemCount(List<Message> list) {
            return list.size();
        }
    };
	
    adapter = new ChatAdapter(this,mutlipleItem,null);
    rc_view.setAdapter(adapter);

### 支持添加点击、长按事件

	adapter.setOnRecyclerViewListener(new OnRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                Log.i("smile", "点击的是：" + position);
            }

            @Override
            public boolean onItemLongClick(final int position) {
                Log.i("smile", "长按的是：" + position);
                return true;
            }
        });

### 设置头部View

    IMutlipleItem<Contact> mutlipleItem = new IMutlipleItem<Contact>() {

        @Override
        public int getItemViewType(int postion, Contact friend) {
            if(postion==0){//新朋友布局
                return 0;
            }else if(postion==1){//群聊布局
                return 1;
            }else{//联系人的list
                return 2;
            }
        }

        @Override
        public int getItemLayoutId(int viewtype) {
            if(viewtype==0){
                return R.layout.header_new_friend;这是一个头部view
            }else if(viewtype==1){
                return R.layout.header_group;//这是一个头部view
            }else{
                return R.layout.item_contact;
            }
        }

        @Override
        public int getItemCount(List<Contact> list) {
            return list.size()+2;//增加两个自定义的头部布局
        }
    };

