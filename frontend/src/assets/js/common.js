const common = {
  sleep: function(ms) {
    const wakeUpTime = Date.now() + ms;
    while (Date.now() < wakeUpTime) {}
  },

  // 와인 type 영문화
  getEType: function(type) {
    switch (type) {
      case '레드':
        return 'Red Wine';
      case '화이트':
        return 'White wine';
      case '스파클링':
        return 'Sparkling';
      default:
        console.log('wine type error!');
        return type;
    }
  },

  // 와인 타입 대표 색
  getTypeColor: function(type) {
    switch (type) {
      case '레드':
        return '#821a33';
      case '화이트':
        return '#ded483';
      case '스파클링':
        return '#89b22b';
      default:
        console.log('wine type error!');
        return '#000';
    }
  },
};

export default common;
