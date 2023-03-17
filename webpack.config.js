/**
 * 此文件没有在项目中使用， 只是为了让 Webstorm 可以识别目录实际位置,提高开发效率
 * 比如识别 @ , ctrl + 鼠标左键可以跳转
 * */
const path = require("path");

function resolve(dir) {
	return path.join(__dirname, dir);
}

module.exports = {
	resolve: {
		alias: {
			"@": resolve("src"),
		},
	},
};
