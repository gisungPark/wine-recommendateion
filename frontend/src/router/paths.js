export default [
  {
    path: '',
    view: 'Main',
    name: 'Main',
  },
  {
    path: '/mypage',
    view: 'Mypage',
    name: 'Mypage',
  },
  {
    path: '/recommendation',
    view: 'Recommendation',
    name: 'Recommendation',
  },
  {
    path: '/wines',
    view: 'Wines',
    name: 'Wines',
  },
  {
  path:'/detail/:wineId',
    view:'WineDetails',
    name:'WineDetials',
    props:true,
  }
];
