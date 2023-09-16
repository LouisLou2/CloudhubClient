package com.cloudhub.cloudclient.model.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FileMeta {

  private String hash;
  private String bucketName;
  private String objectKey;
  private long count;
}
