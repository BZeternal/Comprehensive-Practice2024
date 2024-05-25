<template>
  <div
    style="
      display: flex;
      flex-direction: column;
      align-items: center;
      width: 100%;
      background-color: #2c3e50;
    "
  >
    <div style="display: flex; width: 100%">
      <div style="flex: 1" ref="mCharts1" class="box"></div>
      <div style="flex: 1" ref="mCharts" class="box"></div>
      <div style="flex: 1" ref="mCharts2" class="box"></div>
    </div>
    <div style="display: flex; width: 100%">
      <div style="flex: 1" ref="mCharts3" class="box"></div>
      <div style="flex: 1" ref="mCharts4" class="box"></div>
    </div>

    <div ref="mCharts5" style="width: 80%; height: 400px" class="box"></div>
  </div>
</template>

<script setup>
import * as reserveApi from "@/api/reserve";
import { ref } from "vue";
import * as echarts from "echarts";

let mCharts = ref();
let mCharts1 = ref();
let mCharts2 = ref();
let mCharts3 = ref();
let mCharts4 = ref();
let mCharts5 = ref();

let today_num = 0;

let option1 = {};
let option2 = {};
let option3 = {};
let option4 = {};
let option5 = {};

const getEveryHourNum = () => {
  reserveApi.getEveryHourNum().then((resp) => {
    today_num = 0;
    let x = [];
    let y = [];
    for (let i = 8; i <= 19; i++) {
      x.push(i + ":00");
      y.push(0);
    }
    resp.forEach((i) => {
      today_num += i.count;
      let t = new Date(i.timeString).getHours();
      y[t - 8] = i.count;
    });
    option1 = {
      series: [
        {
          type: "gauge",
          startAngle: 180,
          endAngle: 0,
          center: ["50%", "75%"],
          radius: "90%",
          min: 0,
          max: 320,
          splitNumber: 8,
          axisLine: {
            lineStyle: {
              width: 6,
              color: [
                [0.25, "#7CFFB2"],
                [0.5, "#58D9F9"],
                [0.75, "#FDDD60"],
                [1, "#FF6E76"],
              ],
            },
          },
          pointer: {
            icon: "path://M12.8,0.7l12,40.1H0.7L12.8,0.7z",
            length: "12%",
            width: 20,
            offsetCenter: [0, "-60%"],
            itemStyle: {
              color: "auto",
            },
          },
          axisTick: {
            length: 12,
            lineStyle: {
              color: "auto",
              width: 2,
            },
          },
          splitLine: {
            length: 20,
            lineStyle: {
              color: "auto",
              width: 5,
            },
          },
          axisLabel: {
            color: "#ecf0f1",
            fontSize: 20,
            distance: -60,
            rotate: "tangential",
            formatter: function (value) {
              if (value === 40) {
                return "空闲";
              } else if (value === 120) {
                return "适中";
              } else if (value === 200) {
                return "拥挤";
              } else if (value === 280) {
                return "爆满";
              }
              return "";
            },
          },
          title: {
            offsetCenter: [0, "-10%"],
            fontSize: 20,
            textStyle: {
              color: "#ecf0f1",
            },
          },
          detail: {
            fontSize: 30,
            offsetCenter: [0, "-35%"],
            valueAnimation: true,
            formatter: function (value) {
              return Math.round(value) + "";
            },
            color: "inherit",
          },
          data: [
            {
              value: today_num,
              name: "今日学车人数",
            },
          ],
        },
      ],
    };
    showChart(mCharts, option1);

    option4 = {
      color: ["#d4a4eb"],
      title: {
        text: "今日各时间学车人数",
        left: "center",
        textStyle: {
          color: "#ecf0f1",
          fontSize: 24,
        },
      },
      tooltip: {
        show: true,
        confine: false,
        triggerOn: "mousemove|click",
        hideDelay: 200,
        backgroundColor: "rgba(0,0,0,0.5)",
        textStyle: {
          color: "#fff",
        },
      },
      xAxis: {
        type: "category",
        data: x,
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      yAxis: {
        type: "value",
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      series: [
        {
          data: y,
          type: "line",
          areaStyle: {},
          smooth: true,
        },
      ],
    };
    showChart(mCharts3, option4);
  });
};

const getSubIdNum = () => {
  reserveApi.getSubIdNum().then((resp) => {
    let x = [];
    let y = [];
    resp.forEach((i) => {
      if (i.subId == 1) {
        x.push("科目一");
      } else if (i.subId == 2) {
        x.push("科目二");
      } else if (i.subId == 3) {
        x.push("科目三");
      } else {
        x.push("科目四");
      }
      y.push(i.count);
    });
    option2 = {
      color: ["#c23531", "#2f4554", "#61a0a8"],
      title: {
        text: "今日各学科学习人数",
        left: "center",
        textStyle: {
          color: "#ecf0f1",
          fontSize: 24,
        },
      },
      tooltip: {
        show: true,
        confine: false,
        triggerOn: "mousemove|click",
        hideDelay: 200,
        backgroundColor: "rgba(0,0,0,0.5)",
        textStyle: {
          color: "#fff",
        },
      },
      xAxis: {
        type: "value",
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      yAxis: {
        type: "category",
        data: x,
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      series: [
        {
          data: y,
          type: "bar",
        },
      ],
    };
    showChart(mCharts1, option2);
  });
};

const getCarTypeNum = () => {
  reserveApi.getCarTypeNum().then((resp) => {
    let x = [];
    let y = [];
    resp.forEach((i) => {
      x.push(i.aCarType);
      y.push(i.count);
    });
    option3 = {
      title: {
        text: "今日各准驾车型学习人数",
        left: "center",
        textStyle: {
          color: "#ecf0f1",
          fontSize: 24,
        },
      },
      tooltip: {
        show: true,
        confine: false,
        triggerOn: "mousemove|click",
        hideDelay: 200,
        backgroundColor: "rgba(0,0,0,0.5)",
        textStyle: {
          color: "#fff",
        },
      },
      xAxis: {
        type: "category",
        data: x,
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      yAxis: {
        type: "value",
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      series: [
        {
          data: y,
          type: "line",
          smooth: true,
          areaStyle: {},
        },
      ],
    };
    showChart(mCharts2, option3);
  });
};

const getPreNum = () => {
  reserveApi.getPreNum().then((resp) => {
    let x = [];
    let y = [];
    resp.forEach((i) => {
      x.push(i.time);
      y.push(i.count);
    });
    option5 = {
      color: ["#fc97af"],
      title: {
        text: "5天内预约人数",
        left: "center",
        textStyle: {
          color: "#ecf0f1",
          fontSize: 24,
        },
      },
      tooltip: {
        show: true,
        confine: false,
        triggerOn: "mousemove|click",
        hideDelay: 200,
        backgroundColor: "rgba(0,0,0,0.5)",
        textStyle: {
          color: "#fff",
        },
      },
      xAxis: {
        type: "category",
        data: x,
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      yAxis: {
        type: "value",
        axisLine: {
          lineStyle: {
            color: "#ecf0f1",
          },
        },
      },
      series: [
        {
          data: y,
          type: "bar",
        },
      ],
    };
    showChart(mCharts4, option5);
  });
};

const refresh_Info = () => {
  getEveryHourNum();
  getSubIdNum();
  getCarTypeNum();
  getPreNum();
};

refresh_Info();

const showChart = (mCharts, option) => {
  let myChart = echarts.getInstanceByDom(mCharts.value);
  if (myChart == null) {
    myChart = echarts.init(mCharts.value);
  }
  myChart.setOption(option);
};

let a = [];
let b = [220, 182, 191, 234, 290, 330, 310];
let c = [280, 232, 201, 354, 190, 430, 420];
for (let i = 0; i < 7; i++) {
  a[i] = b[i] + c[i];
}

// 指定图表的配置项和数据
var option = {
  backgroundColor: "#2c3e50",
  title: {
    text: "学员周练习情况",
    left: "center",
    textStyle: {
      color: "#ecf0f1",
      fontSize: 24,
    },
  },
  tooltip: {
    trigger: "axis",
    axisPointer: {
      type: "shadow",
    },
  },
  legend: {
    data: ["练习次数", "合格次数", "未合格次数"],
    top: "10%",
    textStyle: {
      color: "#ecf0f1",
    },
  },
  toolbox: {
    show: true,
    feature: {
      saveAsImage: { title: "Save" },
    },
  },
  grid: {
    left: "3%",
    right: "4%",
    bottom: "3%",
    containLabel: true,
  },
  xAxis: {
    type: "category",
    boundaryGap: false,
    data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
    axisLine: {
      lineStyle: {
        color: "#ecf0f1",
      },
    },
  },
  yAxis: {
    type: "value",
    axisLine: {
      lineStyle: {
        color: "#ecf0f1",
      },
    },
  },
  series: [
    {
      name: "练习次数",
      type: "line",
      stack: "Total",
      data: a,
      smooth: true,
      lineStyle: {
        color: "#3498db",
        width: 3,
      },
      itemStyle: {
        borderWidth: 3,
        borderColor: "#3498db",
        color: "#3498db",
      },
    },
    {
      name: "合格次数",
      type: "bar",
      stack: "Total",
      data: b,
      itemStyle: {
        color: "#2ecc71",
      },
    },
    {
      name: "未合格次数",
      type: "bar",
      stack: "Total",
      data: c,
      itemStyle: {
        color: "#e74c3c",
      },
    },
  ],
  animationEasing: "elasticOut",
  animationDuration: 2000,
};

setTimeout(() => {
  var myChart = echarts.init(mCharts5.value);
  myChart.setOption(option);
}, 500);
</script>

<style scoped>
.box {
  height: 350px;
}

@media screen and (max-width: 700px) {
  .box {
    height: 200px;
    width: 200px;
    margin-bottom: 30px;
  }
}
</style>
