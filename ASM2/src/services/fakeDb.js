// ===== USERS =====
export const users = [
  {
    id: 'U001',
    email: 'kienle@gmail.com',
    password: '123456',
    name: 'Lê Trung Kiên',
    role: 'user'
  },
  {
    id: 'U002',
    email: 'vanhieu@gmail.com',
    password: '123456',
    name: 'Nguyễn Văn Hiếu',
    role: 'admin'
  },
  {
    id: 'U003',
    email: 'dinhhieu@gmail.com',
    password: '123456',
    name: 'Lê Đình Hiếu',
    role: 'user'
  },

    {
    id: 'U004',
    email: 'tqBinh@gmail.com',
    password: '123456',
    name: 'Trần Quang Bình',
    role: 'user'
  }
]

// ===== CATEGORIES =====
export const categories = [
  { id: 1, name: 'Quán ăn', slug: 'quan-an' },
  { id: 2, name: 'Địa điểm quay', slug: 'dia-diem-quay' },
  { id: 3, name: 'Đồ thủ công', slug: 'do-thu-cong' }
]

// ===== POSTS =====
export const posts = [
  {
    id: 'P001',
    title: 'Quán ăn gần QTSC',
    author: 'Lê Trung Kiên',
    image: '/images/quan-an/com-tam.jpg',
    content: 'Quán cơm tấm ngon, giá sinh viên, phù hợp ăn trưa.',
    createdAt: '2024-01-01',
    category: 'quan-an',
    active: true
  },
  {
    id: 'P002',
    title: 'Quán ăn mở khuya',
    author: 'Lê Trung Kiên',
    image: '/images/quan-an/quan-khuya.jpg',
    content: 'Phù hợp chạy deadline ban đêm, mở tới khuya.',
    createdAt: '2024-01-02',
    category: 'quan-an',
    active: true
  },
  {
    id: 'P003',
    title: 'Quán ăn sinh viên giá rẻ',
    author: 'Lê Đình Hiếu',
    image: '/images/quan-an/banh-mi.jpg',
    content: 'Giá chỉ từ 20k, phù hợp sinh viên.',
    createdAt: '2024-01-03',
    category: 'quan-an',
    active: true
  },

  {
    id: 'P004',
    title: 'Địa điểm quay video yên tĩnh',
    author: 'Lê Trung Kiên',
    image: '/images/dia-diem-quay/khu-a.jpg',
    content: 'Không gian yên tĩnh, ánh sáng tự nhiên.',
    createdAt: '2024-01-04',
    category: 'dia-diem-quay',
    active: true
  },
  {
    id: 'P005',
    title: 'Phòng học trống buổi tối',
    author: 'Nguyễn Văn Hiếu',
    image: '/images/dia-diem-quay/phong-hoc.jpg',
    content: 'Buổi tối ít người, phù hợp quay phim.',
    createdAt: '2024-01-05',
    category: 'dia-diem-quay',
    active: true
  },
  {
    id: 'P006',
    title: 'Khuôn viên nhiều ánh sáng',
    author: 'Nguyễn Văn Hiếu',
    image: '/images/dia-diem-quay/khuon-vien.jpg',
    content: 'Quay ban ngày rất đẹp, ánh sáng tự nhiên.',
    createdAt: '2024-01-06',
    category: 'dia-diem-quay',
    active: true
  },

  {
    id: 'P007',
    title: 'Tiệm in ấn nhanh',
    author: 'Lê Đình Hiếu',
    image: '/images/do-thu-cong/in-an.jpg',
    content: 'In lấy liền trong ngày, giá hợp lý.',
    createdAt: '2024-01-07',
    category: 'do-thu-cong',
    active: true
  },
  {
    id: 'P008',
    title: 'Mua bìa mô hình',
    author: 'Lê Trung Kiên',
    image: '/images/do-thu-cong/bia-mo-hinh.jpg',
    content: 'Giá rẻ, nhiều kích thước.',
    createdAt: '2024-01-08',
    category: 'do-thu-cong',
    active: true
  },
  {
    id: 'P009',
    title: 'Cắt laser chính xác',
    author: 'Lê Đình Hiếu',
    image: '/images/do-thu-cong/cat-laser.jpg',
    content: 'Phù hợp làm đồ án, độ chính xác cao.',
    createdAt: '2024-01-09',
    category: 'do-thu-cong',
    active: true
  }
]

// ===== COMMENTS =====
export const comments = [
  {
    id: 'C001',
    postId: 'P001',
    author: 'TLê Trung Kiên',
    content: 'Bài viết rất hữu ích!',
    createdAt: '2024-01-02'
  },
  {
    id: 'C002',
    postId: 'P001',
    author: 'Lê Đình Hiếu',
    content: 'Mình đã thử, đúng như bài viết.',
    createdAt: '2024-01-03'
  },
  {
    id: 'C003',
    postId: 'P004',
    author: 'Lê Trung Kiên',
    content: 'Quay buổi chiều ánh sáng rất đẹp.',
    createdAt: '2024-01-05'
  },
  {
    id: 'C004',
    postId: 'P007',
    author: 'Lê Đình Hiếu',
    content: 'Tiệm này hỗ trợ sinh viên khá tốt.',
    createdAt: '2024-01-08'
  }
]

// ===== FAVORITES (LIKE) =====
export const favorites = [
  { postId: 'P001', user: 'Lê Trung Kiên' },
  { postId: 'P004', user: 'Lê Trung Kiên' },
  { postId: 'P007', user: 'Lê Trung Kiên' },
  { postId: 'P003', user: 'Lê Đình Hiếu' },
  { postId: 'P009', user: 'Nguyễn Văn Hiếu' }
]
