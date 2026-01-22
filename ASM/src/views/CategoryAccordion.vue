<template>
  <div class="container mt-4">
    <h3 class="mb-3">Bài viết theo chủ đề</h3>

    <div class="accordion" id="categoryAccordion">

      <div
        class="accordion-item"
        v-for="cat in categories"
        :key="cat.id"
      >
        <h2 class="accordion-header">
          <button
            class="accordion-button collapsed"
            data-bs-toggle="collapse"
            :data-bs-target="'#cat-' + cat.id"
          >
            {{ cat.name }}
          </button>
        </h2>

        <div
          :id="'cat-' + cat.id"
          class="accordion-collapse collapse"
        >
          <div class="accordion-body">

            <!-- BÀI VIẾT -->
            <div
              class="card mb-3"
              v-for="post in cat.posts"
              :key="post.id"
            >
              <router-link
                :to="`/post/${post.id}`"
                class="text-decoration-none text-dark"
              >
                <div class="row g-0">
                  <div class="col-md-4">
                    <img
                      :src="post.image"
                      class="img-fluid rounded-start"
                      alt="post image"
                    />
                  </div>

                  <div class="col-md-8">
                    <div class="card-body">
                      <h6 class="card-title">{{ post.title }}</h6>
                      <p class="card-text small text-muted">
                        {{ post.content }}
                      </p>
                    </div>
                  </div>
                </div>
              </router-link>
            </div>

            <router-link
              :to="`/category/${cat.slug}`"
              class="btn btn-sm btn-outline-primary"
            >
              Xem thêm
            </router-link>

          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script setup>
const categories = [
  {
    id: 1,
    name: '🍜 Quán ăn',
    slug: 'quan-an',
    posts: [
      {
        id: 101,
        title: 'Cơm tấm gần QTSC',
        content: 'Ngon, giá sinh viên',
        image: '/images/quan-an/com-tam.jpg'
      },
      {
        id: 105,
        title: 'Bánh mì 20k',
        content: 'Ăn sáng tiết kiệm',
        image: '/images/quan-an/banh-mi.jpg'
      },
      {
        id: 106,
        title: 'Quán mở khuya',
        content: 'Chạy deadline',
        image: '/images/quan-an/quan-khuya.jpg'
      }
    ]
  },

  {
    id: 2,
    name: '🎥 Địa điểm quay',
    slug: 'dia-diem-quay',
    posts: [
      {
        id: 201,
        title: 'Tầng 2 khu A',
        content: 'Yên tĩnh buổi chiều',
        image: '/images/dia-diem-quay/khu-a.jpg'
      },
      {
        id: 202,
        title: 'Khuôn viên nhiều ánh sáng',
        content: 'Quay ban ngày đẹp',
        image: '/images/dia-diem-quay/khuon-vien.jpg'
      },
      {
        id: 203,
        title: 'Phòng học trống',
        content: 'Buổi tối ít người',
        image: '/images/dia-diem-quay/phong-hoc.jpg'
      }
    ]
  },

  {
    id: 3,
    name: '🛠️ Đồ thủ công',
    slug: 'do-thu-cong',
    posts: [
      {
        id: 301,
        title: 'Tiệm in ấn nhanh',
        content: 'Lấy liền trong ngày',
        image: '/images/do-thu-cong/in-an.jpg'
      },
      {
        id: 302,
        title: 'Mua bìa mô hình',
        content: 'Giá rẻ',
        image: '/images/do-thu-cong/bia-mo-hinh.jpg'
      },
      {
        id: 303,
        title: 'Cắt laser',
        content: 'Phù hợp đồ án',
        image: '/images/do-thu-cong/cat-laser.jpg'
      }
    ]
  }
]
</script>
