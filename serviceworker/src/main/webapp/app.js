/**
 * 
 */

if ('serviceWorker' in navigator) {
	navigator.serviceWorker.register(
		'./sw.js', {scope: '/WEB-INF/*'}
	).then(success, failure);
	
}