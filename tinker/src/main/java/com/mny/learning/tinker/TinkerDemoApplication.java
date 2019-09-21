package com.mny.learning.tinker;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author mny on 2019-05-16.
 * Email：mny9@outlook.com
 * Desc:
 */
public class TinkerDemoApplication extends TinkerApplication {
    public TinkerDemoApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.mny.learning.tinker.TinkerApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
