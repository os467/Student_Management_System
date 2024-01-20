var REG_TOOL = {
    /*匹配保留小数点后两位的实数,不以0开头的数字或0本身,匹配暂无成绩*/
      score: function (str){
          /*^暂无成绩$|^0$|^[^0]\d+(.\d{1,2})?$*/
          return new RegExp("^暂无成绩$|^0$|^[^0]\\d+(.\\d{1,2})?$").test(str);
      }
}