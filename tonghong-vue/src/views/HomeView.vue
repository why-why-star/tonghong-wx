<template>
  <div ref="echarRef" style="width:800px;height:500px;"></div>
</template>

<script>

import * as echarts from 'echarts';

export default{
  data () {
    return {
      myChart:{}
    }
  },
  mounted() {
    this.myechears();
  },
  methods:{
    
    myechears(){

      const main = this.$refs.echarRef;
      const myChars = echarts.init(main);

      var option;

      option = {
        title: {
          text: '统计',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [
              { value: 1048, name: 'Search Engine' },
              { value: 735, name: 'Direct' },
              { value: 580, name: 'Email' },
              { value: 484, name: 'Union Ads' },
              { value: 300, name: 'Video Ads' }
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      };
      myChars.setOption(option);
    }
    
  }
  
}
</script>


