package com.cloudhub.cloudclient.model.storage;

public class FolderInfo extends BaseInfo{

    public FolderInfo() {
        super();
    }

    public FolderInfo(long userId, long id, long pid, String name,long timeStamp) {
        super(id, 0,userId, name,timeStamp,pid);
    }
}
