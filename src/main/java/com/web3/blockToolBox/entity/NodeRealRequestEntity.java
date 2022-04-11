package com.web3.blockToolBox.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NodeRealRequestEntity {
    private String jsonrpc;

    private String method;

    private String[] params;

    private int id;
}
