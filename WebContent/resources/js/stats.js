/**
 * 
 */
 function generateColor(){
 	let o = Math.round, r = Math.random, s = 255;
    return 'rgba(' + o(r()*s) + ',' + o(r()*s) + ',' + o(r()*s) + ',' + r().toFixed(1) + ')';
 	
 }
 
function cateChart(id, cateLabels=[], cateInfos=[]){
	const colors=[]
	for(let i=0; i < cateLabels.length; i++)
		colors.push(generateColor())
	const data = {
  	labels: cateLabels,
 	 datasets: [{
	    label: 'Trạng thái đơn hàng',
	    data: cateInfos,
	    backgroundColor: colors,
	    hoverOffset: 4
	  }]
	};

	const config = {
	  type: 'doughnut',
	  data: data,
	};

	let ctx = document.getElementById(id).getContext("2d")
	new Chart(ctx, config)
}

function areaChart(id, cateLabels=[], cateInfos=[]){
	const colors=[]
	for(let i=0; i < cateLabels.length; i++)
		colors.push(generateColor())
	const data = {
  labels: cateLabels,
	  datasets: [{
	    label: 'Trạng thái đơn hàng',
	    data: cateInfos,
	    backgroundColor: colors
	  }]
	};
	
	const config = {
  type: 'polarArea',
  data: data,
  options: {}
};
	
	let ctx = document.getElementById(id).getContext("2d")
	new Chart(ctx, config)

}

function barChart(id, cateLabels=[], cateInfos=[]){
	const colors=[]
	for(let i=0; i < cateLabels.length; i++)
		colors.push(generateColor())
	const data = {
  	labels:cateLabels,
 	 datasets: [{
	    label: 'Doanh thu',
	    data: cateInfos,
	    backgroundColor: colors,
	    hoverOffset: 4
	  }]
	};

	const config = {
	  type: 'bar',
	  data: data,
	};

	let ctx = document.getElementById(id).getContext("2d")
	new Chart(ctx, config)
}
