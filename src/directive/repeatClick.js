export default {
  /**
   * 防止按钮多次点击,demo
   *   <el-button v-reClick icon="Refresh">点击</el-button>
   *   <el-button v-reClick="(10000)" icon="Refresh">点击</el-button>
   *
   * @param el
   * @param binding
   * @param vnode
   */
  mounted(el, binding, vnode) {
    el.addEventListener('click', e => {
      el.classList.add('is-disabled')
      el.disabled = true
      setTimeout(() => {
        el.disabled = false
        el.classList.remove('is-disabled')
      }, binding.value || 3000) // 默认3000毫秒(3秒)不能再次点击[注意这个值不要特别特别大,否则会立即实现]
    })
  }
}
