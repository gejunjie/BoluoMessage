package com.benboer.boluo.factory.presenter.message;

import com.benboer.boluo.factory.data.helper.UserHelper;
import com.benboer.boluo.factory.data.message.MessageRepository;
import com.benboer.boluo.factory.model.db.Message;
import com.benboer.boluo.factory.model.db.User;

/**
 * Created by BenBoerBoluojiushiwo on 2019/6/6.
 */
public class ChatUserPresenter extends ChatPresenter<ChatContract.UserView>
        implements ChatContract.Presenter{
    public ChatUserPresenter(ChatContract.UserView view, String receiverId) {
        // 数据源，View，接收者，接收者的类型
        super(new MessageRepository(receiverId),
                view,
                receiverId,
                Message.RECEIVER_TYPE_NONE);
    }

    @Override
    public void start() {
        super.start();
        User user = UserHelper.findFromLocal(mReceiverId);
        getView().onInit(user);
    }
}
