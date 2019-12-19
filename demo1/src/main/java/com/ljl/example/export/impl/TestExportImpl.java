package com.ljl.example.export.impl;

import com.ljl.example.common.SoaResponse;
import com.ljl.example.export.TestExport;
import com.ljl.example.facade.TestServiceFacade;
import com.ljl.example.util.SoaResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("testExportImpl")
public class TestExportImpl implements TestExport {
    @Autowired
    private TestServiceFacade testServiceFacade;
    @Override
    public SoaResponse<String, ?> getTest() {
        return SoaResponseUtil.ok(testServiceFacade.getTest());
    }
}
