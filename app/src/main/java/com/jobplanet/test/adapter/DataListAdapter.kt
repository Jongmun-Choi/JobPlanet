package com.jobplanet.test.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jobplanet.test.databinding.*
import com.jobplanet.test.model.*


class DataListAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(Companion) {

    val VIEW_TYPE_COMPANY = 0
    val VIEW_TYPE_HORIZONTAL_THEME = 1
    val VIEW_TYPE_INTERVIEW = 2
    val VIEW_TYPE_JOB_POSTING = 3
    val VIEW_TYPE_REVIEW = 4
    val VIEW_TYPE_SALARY = 5

    class CompanyViewHolder(val binding : ItemCellTypeCompanyBinding) : RecyclerView.ViewHolder(binding.root)
    class HorizontalViewHolder(val binding : ItemCellTypeHorizontalThemeBinding) : RecyclerView.ViewHolder(binding.root)
    class SalaryViewHolder(val binding : ItemCellTypeSalaryBinding) : RecyclerView.ViewHolder(binding.root)
    class InterviewViewHoler(val binding : ItemCellTypeInterviewBinding) : RecyclerView.ViewHolder(binding.root)
    class ReviewViewHolder(val binding : ItemCellTypeReviewBinding) : RecyclerView.ViewHolder(binding.root)
    class JobPostingViewHolder(val binding : ItemCellTypeJobPostingBinding) : RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when(newItem) {
                is CompanyData -> {
                   oldItem is CompanyData
                }
                is HorizontalThemeData -> {
                    oldItem is HorizontalThemeData
                }
                is InterviewData -> {
                    oldItem is InterviewData
                }
                is ReviewData -> {
                    oldItem is ReviewData
                }
                is SalaryData -> {
                    oldItem is SalaryData
                }
                else -> {
                    oldItem is JobPosting
                }

            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when(newItem) {
                is CompanyData -> {
                    oldItem is CompanyData && oldItem.company_id == newItem.company_id
                }
                is HorizontalThemeData -> {
                    oldItem is HorizontalThemeData && oldItem.themes.size == newItem.themes.size
                }
                is InterviewData -> {
                    oldItem is InterviewData && oldItem.company_id == newItem.company_id
                }
                is ReviewData -> {
                    oldItem is ReviewData && oldItem.company_id == newItem.company_id
                }
                is SalaryData -> {
                    oldItem is SalaryData && oldItem.company_id == newItem.company_id
                }
                else -> {
                    oldItem is JobPosting && oldItem.company_id == (newItem as JobPosting).company_id
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when(viewType) {
            VIEW_TYPE_COMPANY ->  {
                val binding = ItemCellTypeCompanyBinding.inflate(layoutInflater, parent, false)
                return CompanyViewHolder(binding)
            }
            VIEW_TYPE_HORIZONTAL_THEME -> {
                val binding = ItemCellTypeHorizontalThemeBinding.inflate(layoutInflater, parent, false)
                return HorizontalViewHolder(binding)
            }
            VIEW_TYPE_INTERVIEW -> {
                val binding = ItemCellTypeInterviewBinding.inflate(layoutInflater, parent, false)
                return InterviewViewHoler(binding)
            }
            VIEW_TYPE_JOB_POSTING -> {
                val binding = ItemCellTypeJobPostingBinding.inflate(layoutInflater, parent, false)
                return JobPostingViewHolder(binding)
            }
            VIEW_TYPE_REVIEW -> {
                val binding = ItemCellTypeReviewBinding.inflate(layoutInflater, parent, false)
                return ReviewViewHolder(binding)
            }
            else -> {
                val binding = ItemCellTypeSalaryBinding.inflate(layoutInflater, parent, false)
                return SalaryViewHolder(binding)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        when(currentItem) {
            is CompanyData -> {
                (holder as CompanyViewHolder).binding.item = currentItem
                holder.binding.executePendingBindings()
            }
            is HorizontalThemeData -> {
                val adapter = HorizontalThemeAdapter()
                (holder as HorizontalViewHolder).binding.item = adapter
                adapter.submitList(currentItem.themes)
                holder.binding.executePendingBindings()
            }
            is InterviewData -> {
                (holder as InterviewViewHoler).binding.item = currentItem
                holder.binding.executePendingBindings()
            }
            is ReviewData -> {
                (holder as ReviewViewHolder).binding.item = currentItem
                holder.binding.executePendingBindings()
            }
            is SalaryData -> {
                (holder as SalaryViewHolder).binding.item = currentItem
                holder.binding.executePendingBindings()
            }
            else -> {
                (holder as JobPostingViewHolder).binding.item = currentItem as JobPosting
                holder.binding.executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is CompanyData -> VIEW_TYPE_COMPANY
            is HorizontalThemeData -> VIEW_TYPE_HORIZONTAL_THEME
            is InterviewData -> VIEW_TYPE_INTERVIEW
            is JobPosting -> VIEW_TYPE_JOB_POSTING
            is ReviewData -> VIEW_TYPE_REVIEW
            else -> VIEW_TYPE_SALARY
        }
    }
}