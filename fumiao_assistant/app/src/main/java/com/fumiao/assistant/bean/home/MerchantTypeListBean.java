package com.fumiao.assistant.bean.home;

import com.fumiao.core.adapter.CoreBean;

import java.util.List;

/**
 * Author: XieBoss
 * Date: 2019/10/11 0011 18:36
 *
 * @Description:
 */
public class MerchantTypeListBean {


        private List<MerchantTypeBean> merchant_type;

        public List<MerchantTypeBean> getMerchant_type() {
            return merchant_type;
        }

        public void setMerchant_type(List<MerchantTypeBean> merchant_type) {
            this.merchant_type = merchant_type;
        }

        public static class MerchantTypeBean extends CoreBean {
            /**
             * name : 生活百货
             * code : C
             * list : [{"name":"超市（非平台类）","mcc":5411},{"name":"成人用品/避孕用品/情趣内衣","mcc":5914},{"name":"国外代购及免税店","mcc":5309},{"name":"会员制批量零售店","mcc":5300},{"name":"平台类综合商城","mcc":5311},{"name":"其他专业零售店","mcc":5999},{"name":"其他综合零售","mcc":5399},{"name":"烟花爆竹","mcc":5984},{"name":"油品燃料经销","mcc":5983},{"name":"杂货店","mcc":5331},{"name":"帐篷和遮阳篷商店","mcc":5998},{"name":"裁缝、修补、改衣制衣","mcc":5697},{"name":"高档时装及奢侈品","mcc":5691},{"name":"各类服装及饰物","mcc":5699},{"name":"行李箱包","mcc":5948},{"name":"假发等饰品","mcc":5698},{"name":"男性服饰","mcc":5611},{"name":"内衣/家居服","mcc":5651},{"name":"女性成衣","mcc":5621},{"name":"配饰商店","mcc":5631},{"name":"皮草皮具","mcc":5681},{"name":"鞋类","mcc":5661},{"name":"鞋类销售平台（批发商）","mcc":5139},{"name":"制服与商务正装定制","mcc":5137},{"name":"壁炉、屏风","mcc":5718},{"name":"玻璃、油漆涂料、墙纸","mcc":5231},{"name":"布料、缝纫用品和其他纺织品（批发商）","mcc":5131},{"name":"草坪和花园用品","mcc":5261},{"name":"窗帘、帷幕、室内装潢","mcc":5714},{"name":"大型仓储式家庭用品卖场","mcc":5200},{"name":"地板和地毯","mcc":5713},{"name":"各种家庭装饰专营","mcc":5719},{"name":"花木栽种用品、苗木和花卉（批发商）","mcc":5193},{"name":"家具/家庭摆设","mcc":5712},{"name":"家用纺织品","mcc":5949},{"name":"家用五金工具","mcc":5251},{"name":"木材与建材商店","mcc":5211},{"name":"未列入其他代码的建材（批发商）","mcc":5039},{"name":"油漆、清漆用品（批发商）","mcc":5198},{"name":"游泳、SPA、洗浴设备","mcc":5996},{"name":"化妆品","mcc":5977},{"name":"男士用品：剃须刀、烟酒具、瑞士军刀","mcc":5997},{"name":"钟表店","mcc":5944},{"name":"珠宝和金银饰品","mcc":5094},{"name":"母婴用品","mcc":5641},{"name":"玩具、游戏用品","mcc":5945},{"name":"家用电器","mcc":5722},{"name":"商用计算机及服务器","mcc":5045},{"name":"手机、通讯设备销售","mcc":4812},{"name":"数码产品及配件","mcc":5732},{"name":"专业摄影器材","mcc":5946},{"name":"报纸、杂志","mcc":5994},{"name":"书、期刊和报纸（批发商）","mcc":5192},{"name":"书籍","mcc":5942},{"name":"音像制品","mcc":5735},{"name":"宠物及宠物用品","mcc":5995},{"name":"瓷器、玻璃和水晶摆件","mcc":5950},{"name":"工艺美术用品","mcc":5970},{"name":"古玩复制品（赝品）","mcc":5937},{"name":"花店","mcc":5992},{"name":"家用电子游戏","mcc":7993},{"name":"旧商品店、二手商品店","mcc":5931},{"name":"乐器","mcc":5733},{"name":"礼品、卡片、纪念品","mcc":5947},{"name":"文物古董","mcc":5932},{"name":"艺术品和画廊","mcc":5971},{"name":"邮票/纪念币","mcc":5972},{"name":"宗教物品","mcc":5973},{"name":"保健品","mcc":5467},{"name":"餐厅、订餐服务","mcc":5812},{"name":"茶叶","mcc":5466},{"name":"酒吧、舞厅、夜总会","mcc":5813},{"name":"酒类","mcc":5921},{"name":"咖啡厅、茶馆","mcc":5815},{"name":"快餐店","mcc":5814},{"name":"面包糕点","mcc":5462},{"name":"其他食品零售","mcc":5499},{"name":"肉、禽、蛋及水产品","mcc":5422},{"name":"乳制品/冷饮","mcc":5451},{"name":"水果店","mcc":5423},{"name":"糖果及坚果商店","mcc":5441},{"name":"烟草/雪茄","mcc":5993},{"name":"宴会提供商","mcc":5811},{"name":"体育用品/器材","mcc":5941},{"name":"运动服饰","mcc":5655},{"name":"自行车及配件","mcc":5940},{"name":"酒精饮料批发商（国际专用）","mcc":5715},{"name":"校园团餐","mcc":5880},{"name":"综合团餐","mcc":5881},{"name":"大型企业批发","mcc":3002}]
             */

            private String name;
            private String code;
            private boolean isSelect = false;
            private List<SubTypeBean> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public List<SubTypeBean> getList() {
                return list;
            }

            public void setList(List<SubTypeBean> list) {
                this.list = list;
            }

            public static class SubTypeBean extends CoreBean{
                /**
                 * name : 超市（非平台类）
                 * mcc : 5411
                 */

                private String name;
                private String mcc;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getMcc() {
                    return mcc;
                }

                public void setMcc(String mcc) {
                    this.mcc = mcc;
                }
            }

    }
}
